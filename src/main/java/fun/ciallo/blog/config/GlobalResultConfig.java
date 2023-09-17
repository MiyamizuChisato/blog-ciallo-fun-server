package fun.ciallo.blog.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import fun.ciallo.blog.common.Result;
import fun.ciallo.blog.common.Status;
import fun.ciallo.blog.common.Return;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

@RestControllerAdvice(basePackages = {
        "fun.ciallo.blog.core.archive.controller",
        "fun.ciallo.blog.core.category.controller",
        "fun.ciallo.blog.core.oauth.controller",
        "fun.ciallo.blog.core.user.controller",
        "fun.ciallo.blog.core.message.controller",
})
public class GlobalResultConfig implements ResponseBodyAdvice<Object> {
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (returnType.getParameterType().isAssignableFrom(Result.class)) {
            return false;
        } else if (returnType.hasMethodAnnotation(Return.class)) {
            Return returnAnnotation = returnType.getMethodAnnotation(Return.class);
            assert returnAnnotation != null;
            return returnAnnotation.value();
        }
        return true;
    }

    @Override
    @SneakyThrows
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Result<Object> result = new Result<>(Status.SUCCESS);
        result.setData(body);
        if (body instanceof String) {
            return objectMapper.writeValueAsString(result);
        }
        return result;
    }
}
