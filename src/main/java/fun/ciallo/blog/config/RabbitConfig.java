package fun.ciallo.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class RabbitConfig {
    public static final String MAIL_VERIFY_QUEUE_NAME = "mail.verify.queue";
    public static final String MAIL_VERIFY_ROUTING_KEY_NAME = "mail.verify.routing.key";
    public static final String MAIL_EXCHANGE_NAME = "mail.exchange";
    @Bean
    public Queue mailVerifyQueue() {
        return new Queue(MAIL_VERIFY_QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(MAIL_EXCHANGE_NAME, true, false);
    }

    @Bean
    public Binding mailVerifyBinding() {
        return BindingBuilder.bind(mailVerifyQueue()).to(mailExchange()).with(MAIL_VERIFY_ROUTING_KEY_NAME);
    }
}
