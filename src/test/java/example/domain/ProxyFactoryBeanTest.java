package example.domain;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ComponentScanConfig.class)
class ProxyFactoryBeanTest {
    @Autowired
    private UserService userServiceTestProxy;

    @Autowired
    private UserDao userDaoJdbc;

    private static List<User> users;

    @BeforeAll
    static void setup() {
        users = List.of(
                new User("bumjin", "박범진", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 0, "test@gmail.com")
                , new User("bumjin2", "박범진2", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 30, "test2@gmail.com")
                , new User("bumjin3", "박범진3", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER, 0, "test3@gmail.com")
                , new User("bumjin4", "박범진4", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD - 1, "test4@gmail.com")
                , new User("123", "박범진5", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD, "test5@gmail.com")
                , new User("bumjin6", "박범진6", "p1", Level.GOLD, Integer.MAX_VALUE, Integer.MAX_VALUE, "test6@gmail.com")
        );
    }

    @BeforeEach
    void setupEach() {
        userDaoJdbc.deleteAll();
    }

    @Test
    void test() {
        users.forEach(user -> userServiceTestProxy.add(user));

        try {
            userServiceTestProxy.upgradeLevels();
        } catch (TestUserServiceException ignored) {}

        users.forEach(user -> checkUpgraded(user, false));
    }

    private void checkUpgraded(final User user, final boolean expected) {
        final User foundUser = userDaoJdbc.get(user.getId());
        if(expected) {
            assertEquals(user.getLevel().nextLevel(), foundUser.getLevel(), user.getId());
        } else {
            assertEquals(user.getLevel(), foundUser.getLevel(), user.getId());
        }
    }
}
