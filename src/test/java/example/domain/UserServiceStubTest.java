package example.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserServiceStubTest {
    private static UserService userService;
    private static StubUserDao userDao;
    private static List<User> originUsers;

    @BeforeAll
    static void setup() {
        // 객체의 레퍼런스를 공유하기 때문에 원본값을 보존하기 위해서 동일한 리스트를 두 벌 만듦.
        originUsers = List.of(
                new User("bumjin", "박범진", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 0, "test@gmail.com")
                , new User("bumjin2", "박범진2", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 30, "test@gmail.com")
                , new User("bumjin3", "박범진3", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER, 0, "test@gmail.com")
                , new User("bumjin4", "박범진4", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD - 1, "test@gmail.com")
                , new User("bumjin5", "박범진5", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD, "test@gmail.com")
                , new User("bumjin6", "박범진6", "p1", Level.GOLD, Integer.MAX_VALUE, Integer.MAX_VALUE, "test@gmail.com")
        );

        // 업데이트 이후에 레벨이 변경될 수 있는 리스트
        final List<User> processedUsers = List.of(
                new User("bumjin", "박범진", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 0, "test@gmail.com")
                , new User("bumjin2", "박범진2", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER - 1, 30, "test@gmail.com")
                , new User("bumjin3", "박범진3", "p1", Level.BASIC, User.MIN_LOGIN_COUNT_FOR_SILVER, 0, "test@gmail.com")
                , new User("bumjin4", "박범진4", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD - 1, "test@gmail.com")
                , new User("bumjin5", "박범진5", "p1", Level.SILVER, Integer.MAX_VALUE, User.MIN_RECOMMEND_COUNT_FOR_GOLD, "test@gmail.com")
                , new User("bumjin6", "박범진6", "p1", Level.GOLD, Integer.MAX_VALUE, Integer.MAX_VALUE, "test@gmail.com")
        );


        userDao = new StubUserDao(processedUsers);
        userService = new UserServiceImpl(userDao, new MockMailSender());

    }

    @Test
    void testUpgradeLevels() {
        userService.upgradeLevels();

        checkUpgraded(originUsers.get(0), false);
        checkUpgraded(originUsers.get(1), false);
        checkUpgraded(originUsers.get(2), true);
        checkUpgraded(originUsers.get(3), false);
        checkUpgraded(originUsers.get(4), true);
        checkUpgraded(originUsers.get(5), false);
    }

    private void checkUpgraded(final User user, final boolean expected) {
        final User foundUser = userDao.get(user.getId());
        if(expected) {
            assertEquals(user.getLevel().nextLevel(), foundUser.getLevel());
        } else {
            assertEquals(user.getLevel(), foundUser.getLevel());
        }
    }
}