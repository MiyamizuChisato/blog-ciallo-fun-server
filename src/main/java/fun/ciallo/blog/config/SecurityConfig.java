package fun.ciallo.blog.config;

import fun.ciallo.blog.security.CustomizeAccessDeniedHandler;
import fun.ciallo.blog.security.CustomizeAuthenticationEntryPoint;
import fun.ciallo.blog.security.JwtAuthenticationTokenFilter;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.PermissionProfileService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
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
import java.util.HashMap;
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
    public WebSecurityCustomizer webSecurityCustomizer() {
        Map<Set<String>, HttpMethod> urlList = getIgnoreAuthUrlList();
        return web -> urlList.forEach((key, value) -> web.ignoring().antMatchers(value, key.toArray(new String[0])));
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
                .exceptionHandling()
                .authenticationEntryPoint(new CustomizeAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomizeAccessDeniedHandler())
                .and()
                .addFilterBefore(
                        new JwtAuthenticationTokenFilter(stringRedisTemplate, permissionProfileService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    private Map<Set<String>, HttpMethod> getIgnoreAuthUrlList() {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        Map<Set<String>, HttpMethod> urls = new HashMap<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            Open open = handlerMethod.getMethodAnnotation(Open.class);
            if (open != null) {
                assert infoEntry.getKey().getPatternsCondition() != null;
                urls.put(infoEntry.getKey().getPatternsCondition().getPatterns(), open.value());
            }
        }
        return urls;
    }
}