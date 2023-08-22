package fun.ciallo.blog.component;

import fun.ciallo.blog.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Component
public class RabbitMQ {
    @Resource
    private JavaMailSender mailSender;
    @Resource
    private MailProperties mailProperties;
    @Resource
    private TemplateEngine templateEngine;

    @RabbitListener(queues = RabbitConfig.MAIL_VERIFY_QUEUE_NAME)
    public void emailVerify(Map<String, String> map) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject(map.get("title"));
        helper.setTo(map.get("taker"));
        helper.setFrom(mailProperties.getUsername());
        Context context = new Context();
        context.setVariable("code", map.get("code"));
        String mailText = templateEngine.process("VerifyMail.html", context);
        helper.setText(mailText, true);
        mailSender.send(mimeMessage);
    }
}
