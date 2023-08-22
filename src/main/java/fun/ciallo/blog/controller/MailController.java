package fun.ciallo.blog.controller;

import cn.hutool.captcha.generator.RandomGenerator;
import fun.ciallo.blog.common.response.BlogServerException;
import fun.ciallo.blog.common.response.ResultStatus;
import fun.ciallo.blog.config.RabbitConfig;
import fun.ciallo.blog.security.Open;
import fun.ciallo.blog.service.UserAuthService;
import fun.ciallo.blog.utils.AssertUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private UserAuthService userAuthService;

    @Open(HttpMethod.GET)
    @GetMapping("/verify/{email}")
    public void sendVerifyCode(@PathVariable String email) {
        AssertUtils.notTrue(userAuthService.existsByEmail(email), new BlogServerException(ResultStatus.USER_REPEAT));
        String key = "register:" + email;
        String code = new RandomGenerator("0123456789", 6).generate();
        stringRedisTemplate.opsForValue().set(key, code, 30, TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("taker", email);
        map.put("code", code);
        map.put("title", "Ciallo.fun");
        rabbitTemplate.convertAndSend(
                RabbitConfig.MAIL_EXCHANGE_NAME,
                RabbitConfig.MAIL_VERIFY_ROUTING_KEY_NAME,
                map
        );
    }
}