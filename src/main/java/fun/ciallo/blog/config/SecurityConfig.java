package fun.ciallo.blog.config;

import fun.ciallo.blog.security.CustomizeAccessDeniedHandler;
import fun.ciallo.blog.security.CustomizeAuthenticationEntryPoint;
import fun.ciallo.blog.security.JwtAuthenticationTokenFilter;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.PermissionProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private PermissionProfileService permissionProfileService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer WebSecurityCustomizer() {
        return web -> web.ignoring()
                .antMatchers(openList().toArray(new String[0]));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomizeAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomizeAccessDeniedHandler())
                .and()
                .addFilterBefore(
                        new JwtAuthenticationTokenFilter(stringRedisTemplate, permissionProfileService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private Set<String> openList() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        Set<String> openUrls = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            Open open = handlerMethod.getMethodAnnotation(Open.class);
            if (open != null) {
                assert infoEntry.getKey().getPatternsCondition() != null;
                openUrls.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
            }
        }
        return openUrls;
    }
}