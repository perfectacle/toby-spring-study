package example.domain;

import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestUserServiceImpl extends UserServiceImpl {
    private String id = "madnite1";

    public TestUserServiceImpl(final UserDao userDaoJdbc,
                               final MailSender mockMailSender) {
        super(userDaoJdbc, mockMailSender);
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    protected void upgrade(final User user) {
        if(user.getId().equals(this.id)) throw new TestUserServiceException();
        super.upgrade(user);
    }

    @Override
    public List<User> getAll() {
        for (final User user : super.getAll()) {
            super.update(user);
        }

        return null;
    }
}
