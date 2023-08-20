package fun.ciallo.blog.security;

import cn.hutool.json.JSONUtil;
import fun.ciallo.blog.common.response.Result;
import fun.ciallo.blog.common.response.ResultStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<Object> result = new Result<>(ResultStatus.USER_PERMISSION_ERROR);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(JSONUtil.toJsonStr(result));
    }
}
