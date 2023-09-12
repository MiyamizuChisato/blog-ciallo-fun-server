package fun.ciallo.blog.component;

import fun.ciallo.blog.common.*;
import fun.ciallo.blog.core.user.dto.UserDto;
import fun.ciallo.blog.core.user.entity.User;
import fun.ciallo.blog.core.user.service.UserService;
import fun.ciallo.blog.utils.CacheUtils;
import fun.ciallo.blog.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private CacheUtils cacheUtils;
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Token annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Token.class);
        } else {
            return true;
        }
        if (annotation == null) {
            return true;
        }
        String token = request.getHeader("token");
        if (token == null) {
            log.info("缺失Token，拒绝访问");
            throw new ServerException(Status.NOT_FOUND_TOKEN);
        }
        if (!TokenUtils.verify(token)) {
            log.info("非法Token，拒绝访问");
            throw new ServerException(Status.ILLEGAL_TOKEN);
        }
        int id = TokenUtils.getUserIdByToken(token);
        UserDto user = cacheUtils.getWithFunction(RedisConstants.USER, id, UserDto.class, userService::getUserDtoById, RedisConstants.TTL_LONG);
        if (user == null) {
            log.info("非法用户，拒绝访问");
            throw new ServerException(Status.ILLEGAL_USER);
        }
        if (annotation.admin() && user.getPrivileges()) {
            log.info("用户无权限，拒绝访问");
            throw new ServerException(Status.FORBIDDEN);
        }
        UserHolder.set(user);
        return true;
    }
}
