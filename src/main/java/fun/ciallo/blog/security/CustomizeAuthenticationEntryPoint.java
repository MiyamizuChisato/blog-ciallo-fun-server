package fun.ciallo.blog.security;

import cn.hutool.json.JSONUtil;
import fun.ciallo.blog.common.response.Result;
import fun.ciallo.blog.common.response.ResultStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<Object> result = new Result<>(ResultStatus.USER_AUTH_ERROR);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
