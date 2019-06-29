package example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

@Configuration
public class TestMailSenderFactory {
    @Bean
    public MailSender testMailSender() {
        return new TestMailSender();
    }
}
