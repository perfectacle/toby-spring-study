package example.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User();
    }

    @Test
    void testUpgradeLevel() {
        final Level[] levels = Level.values();
        for (final Level level : levels) {
            user.setLevel(level);

            if (level.nextLevel() == null) {
                assertThrows(IllegalArgumentException.class, () -> user.upgrade());
            } else {
                user.upgrade();
                assertEquals(level.nextLevel(), user.getLevel());
            }
        }
    }

    @Test
    void testCanUpgrade() {
        final Level[] levels = Level.values();
        for (final Level level : levels) {
            final User user = new User();
            user.setLevel(level);
            assertFalse(user.canUpgrade());

            switch (level) {
                case BASIC:
                    user.setLogin(User.MIN_LOGIN_COUNT_FOR_SILVER - 1);
                    assertFalse(user.canUpgrade());

                    user.setLogin(User.MIN_LOGIN_COUNT_FOR_SILVER);
                    assertTrue(user.canUpgrade());

                    break;
                case SILVER:
                    user.setRecommend(User.MIN_RECOMMEND_COUNT_FOR_GOLD - 1);
                    assertFalse(user.canUpgrade());

                    user.setRecommend(User.MIN_RECOMMEND_COUNT_FOR_GOLD);
                    assertTrue(user.canUpgrade());

                    break;
                case GOLD:
                    user.setLogin(Integer.MAX_VALUE);
                    assertFalse(user.canUpgrade());

                    user.setRecommend(Integer.MAX_VALUE);
                    assertFalse(user.canUpgrade());

                    break;
                default:
                    assertThrows(IllegalArgumentException.class, user::canUpgrade);
            }
        }
    }
}