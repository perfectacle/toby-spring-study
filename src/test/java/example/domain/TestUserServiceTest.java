package example.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.mail.MailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ComponentScanConfig.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
class TestUserServiceTest {


//    @Autowired
//    private TestUserServiceImpl testUserService;

    @Autowired
    private UserService testUserServiceImpl;

    @Autowired
    private UserDaoJdbc userDaoJdbc;

    @Autowired
    private MailSender mockMailSender;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private static List<User> users;

    static class Asd {
        public String a;

        public Asd(final String a) {
            this.a = a;
        }
    }

    @BeforeAll
    static void setup() {
        List.of(1, 2, 3).stream().map(i -> {
            System.out.println(i);
            return i;
        });

        final StringBuilder sb = new StringBuilder("a");
        sb.append("b");
        final String s2 = sb.toString();

        final Asd a = new Asd("a");
        final Asd b = new Asd("a");

        assertNotSame(a.a, b.a);

        users = List.of(
                new User("bumjin", "박범진", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 0, "test@gmail.com")
                , new User("bumjin2", "박범진2", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 30, "test2@gmail.com")
                , new User("bumjin3", "박범진3", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER, 0, "test3@gmail.com")
                , new User("bumjin4", "박범진4", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD - 1, "test4@gmail.com")
                , new User("bumjin5", "박범진5", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD, "test5@gmail.com")
                , new User("bumjin6", "박범진6", "p1", Level.GOLD, Integer.MAX_VALUE, Integer.MAX_VALUE, "test6@gmail.com")
        );
    }

    @BeforeEach
    void setupEach() {

    }
//
//    @Test
//    void bean() {
//        assertNotNull(testUserService);
//    }
//
//    @Test
//    void upgradeAllOrNothing() throws SQLException {
//        testUserService.setId(users.get(4).getId());
//
//        users.forEach(user -> testUserService.add(user));
//
//        try {
//            testUserService.upgradeLevels();
//            fail("정상적으로 종료될리가 없단 말이다!!");
//        } catch (final TestUserServiceException ignore) {}
//
//        users.forEach(user -> checkUpgraded(user, false));
//    }
//
//    private void checkUpgraded(final User user, final boolean expected) {
//        final User foundUser = userDaoJdbc.get(user.getId());
//        if(expected) {
//            assertEquals(user.getLevel().nextLevel(), foundUser.getLevel(), user.getId());
//        } else {
//            assertEquals(user.getLevel(), foundUser.getLevel(), user.getId());
//        }
//    }
//
//    @Test
//    void testUpgradeLevels() {
//        users.forEach(user -> userDaoJdbc.add(user));
//        testUserService.upgradeLevels();
//
//        if(this.mockMailSender instanceof MockMailSender) {
//            final MockMailSender mockMailSender = (MockMailSender) this.mockMailSender;
//            final List<String> requests = mockMailSender.getRequests();
//
//            assertEquals(2, requests.size());
//            assertEquals(users.get(2).getEmail(), requests.get(0));
//            assertEquals(users.get(4).getEmail(), requests.get(1));
//        }
//    }

//    @Test
//    void testReadOnly() {
//        users.forEach(user -> userDaoJdbc.add(user));
//
//        assertThrows(TransientDataAccessResourceException.class, () -> testUserServiceImpl.getAll());
//    }

    @Test
    @Order(1)
    void delete() {
        userDaoJdbc.deleteAll();
    }

    @Test
    @Transactional
    @Order(2)
    void transactionSync() {
        assertEquals(0, userDaoJdbc.getCount());

        users.forEach(user -> userDaoJdbc.add(user));

        assertNotEquals(0, userDaoJdbc.getCount());
    }

    @Test
    @Order(3)
    void transactionSyncAfter() {
        assertNotEquals(0, userDaoJdbc.getCount());
    }
}