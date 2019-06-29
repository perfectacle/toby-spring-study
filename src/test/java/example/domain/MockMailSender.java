package example.domain;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MockMailSender implements MailSender {
    private List<String> requests = new ArrayList<>();

    public List<String> getRequests() {
        return requests;
    }

    @Override
    public void send(final SimpleMailMessage simpleMessage) throws MailException {
        requests.add(Objects.requireNonNull(simpleMessage.getTo())[0]);
    }

    @Override
    public void send(final SimpleMailMessage... simpleMessages) throws MailException {
        for (final SimpleMailMessage simpleMessage : simpleMessages) {
            requests.add(Objects.requireNonNull(simpleMessage.getTo())[0]);
        }
    }
}
