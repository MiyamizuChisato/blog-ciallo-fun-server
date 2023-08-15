package fun.ciallo.blog.security;

import cn.hutool.json.JSONUtil;
import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.service.PermissionProfileService;
import fun.ciallo.blog.utils.JwtUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    public JwtAuthenticationTokenFilter(StringRedisTemplate stringRedisTemplate, PermissionProfileService permissionProfileService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.permissionProfileService = permissionProfileService;
    }

    private final PermissionProfileService permissionProfileService;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        if (StringUtils.hasLength(token)) {
            if (JwtUtils.verify(token)) {
                loadAndRefreshAuthority(token);
            } else {
                throw new BlogServerException(ResultStatus.USER_AUTH_ERROR);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void loadAndRefreshAuthority(String token) {
        String permissionJson = stringRedisTemplate.opsForValue().get("permission:" + token);
        List<String> permissions;
        if (StringUtils.hasLength(permissionJson)) {
            permissions = JSONUtil.toList(permissionJson, String.class);
        } else {
            permissions = permissionProfileService.listPermissionByUserId(JwtUtils.getUserIdByToken(token));
        }
        stringRedisTemplate.opsForValue().set("permission:" + token, JSONUtil.toJsonStr(permissions), Duration.ofMinutes(20L));
        List<SimpleGrantedAuthority> authorities = permissions.stream().map(SimpleGrantedAuthority::new).toList();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(null, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
