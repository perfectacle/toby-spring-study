package example.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// 1. 어플리케이션 컨텍스트 생성
@ExtendWith(SpringExtension.class)
// 2. 빈 설정
@ContextConfiguration(classes = {TxFactoryBeanConfig.class, TxFactoryBean.class, TestUserServiceImpl.class, UserServiceImpl.class, UserDaoJdbc.class, DaoFactory.class, TransactionManagerFactory.class, TestMailSenderFactory.class})
class UserServiceTest {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserService userService;

    @Autowired
    private TestUserServiceImpl testUserService;

    @Autowired
    private UserDao userDaoJdbc;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private static List<User> users;

    @BeforeAll
    static void setup() {
        users = List.of(
                new User("bumjin", "박범진", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 0, "test@gmail.com")
                , new User("bumjin2", "박범진2", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 30, "test@gmail.com")
                , new User("bumjin3", "박범진3", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER, 0, "test@gmail.com")
                , new User("bumjin4", "박범진4", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD - 1, "test@gmail.com")
                , new User("bumjin5", "박범진5", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD, "test@gmail.com")
                , new User("bumjin6", "박범진6", "p1", Level.GOLD, Integer.MAX_VALUE, Integer.MAX_VALUE, "test@gmail.com")
        );
    }

    @BeforeEach
    void setupEach() {
        // 4. 테스트에 의존성이 없게 끔 DB 정리
        userDaoJdbc.deleteAll();
    }

    @Test
    void testUpgradeLevels() {
        // 5. 테스트 데이터 DB에 생성
        users.forEach(user -> userDaoJdbc.add(user));

        // 우리가 실제로 테스트하고 싶은 로직
        userService.upgradeLevels();

        checkUpgraded(users.get(0), false);
        checkUpgraded(users.get(1), false);
        checkUpgraded(users.get(2), true);
        checkUpgraded(users.get(3), false);
        checkUpgraded(users.get(4), true);
        checkUpgraded(users.get(5), false);
    }

    private void checkUpgraded(final User user, final boolean expected) {
        // 7. DB에서 데이터 조회
        final User foundUser = userDaoJdbc.get(user.getId());
        if(expected) {
            assertEquals(user.getLevel().nextLevel(), foundUser.getLevel());
        } else {
            assertEquals(user.getLevel(), foundUser.getLevel());
        }
    }

    @Test
    void upgradeAllWithProxy() throws SQLException {
        // 5. 테스트 데이터 DB에 생성
        users.forEach(user -> userDaoJdbc.add(user));

        // 우리가 실제로 테스트하고 싶은 로직
        userService.upgradeLevels();

        checkUpgraded(users.get(0), false);
        checkUpgraded(users.get(1), false);
        checkUpgraded(users.get(2), true);
        checkUpgraded(users.get(3), false);
        checkUpgraded(users.get(4), true);
        checkUpgraded(users.get(5), false);
    }

    @Test
    @DirtiesContext
    void upgradeNothingWithProxy() {
        testUserService.setId(users.get(4).getId());

        final TxFactoryBean factoryBean = context.getBean("&txFactoryBean", TxFactoryBean.class);
        factoryBean.setTarget(testUserService);
        factoryBean.setPattern("upgradeLevels");
        factoryBean.setServiceInterface(UserService.class);
        factoryBean.setTransactionManager(transactionManager);

        final UserService userService = (UserService) factoryBean.getObject();

        // 5. 테스트 데이터 DB에 생성
        users.forEach(user -> userDaoJdbc.add(user));

        // 우리가 실제로 테스트하고 싶은 로직
        try {
            userService.upgradeLevels();
        } catch (final Exception ignore) {}

        checkUpgraded(users.get(0), false);
        checkUpgraded(users.get(1), false);
        checkUpgraded(users.get(2), false);
        checkUpgraded(users.get(3), false);
        checkUpgraded(users.get(4), false);
        checkUpgraded(users.get(5), false);
    }
}