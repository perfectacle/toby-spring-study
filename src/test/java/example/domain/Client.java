package example.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class, UserDaoJdbc.class})
class Client {
    @Autowired
    private UserDao userDaoJdbc2;
    @Autowired
    private DataSource dataSourceBean;

    private static User user;
    private static User user2;
    private static User user3;

    @BeforeAll
    static void setupAll() {
        user = new User("whiteship", "백기선", "married", Level.BASIC, 1, 0, "test@gmail.com");
        user2 = new User("whiteship2", "백기선", "married", Level.SILVER, 55, 10, "test@gmail.com");
        user3 = new User("whiteship3", "백기선", "married", Level.GOLD, 100, 40, "test@gmail.com");
    }

    @BeforeEach
    void setup() {
        userDaoJdbc2.deleteAll();
    }

    @Test
    void testAdd() {
        userDaoJdbc2.add(user);

        assertEquals(1, userDaoJdbc2.getCount());
    }

    @Test
    void testGet() {
        userDaoJdbc2.add(user);
        userDaoJdbc2.add(user2);

        User user3 = userDaoJdbc2.get(user.getId());
        User user4 = userDaoJdbc2.get(user2.getId());

        assertEquals(user.getId(), user3.getId());
        assertEquals(user.getName(), user3.getName());
        assertEquals(user.getPassword(), user3.getPassword());

        assertEquals(user2.getId(), user4.getId());
        assertEquals(user2.getName(), user4.getName());
        assertEquals(user2.getPassword(), user4.getPassword());
    }

    @Test
    void testGetEmpty() {
        assertEquals(0, userDaoJdbc2.getCount());

        assertThrows(EmptyResultDataAccessException.class, () -> userDaoJdbc2.get(""));
    }

    @Test
    void countingAndDeleteTest() {
        assertEquals(0, userDaoJdbc2.getCount());

        userDaoJdbc2.add(user);
        assertEquals(1, userDaoJdbc2.getCount());

        userDaoJdbc2.add(user2);
        assertEquals(2, userDaoJdbc2.getCount());

        userDaoJdbc2.add(user3);
        assertEquals(3, userDaoJdbc2.getCount());

        userDaoJdbc2.deleteAll();

        assertEquals(0, userDaoJdbc2.getCount());
    }

    @Test
    void getAllTest() {
        List<User> all = userDaoJdbc2.getAll();
        assertEquals(0, all.size());

        userDaoJdbc2.add(user3);
        all = userDaoJdbc2.getAll();
        assertEquals(1, all.size());
        assertEquals(user3, all.get(0));

        userDaoJdbc2.add(user2);
        all = userDaoJdbc2.getAll();
        assertEquals(2, all.size());
        assertEquals(user2, all.get(0));
        assertEquals(user3, all.get(1));

        userDaoJdbc2.add(user);
        all = userDaoJdbc2.getAll();
        assertEquals(3, all.size());
        assertEquals(user, all.get(0));
        assertEquals(user2, all.get(1));
        assertEquals(user3, all.get(2));
    }

    @Test
    void testDuplicatedKey() {
        userDaoJdbc2.add(user);
        assertThrows(DuplicateKeyException.class, () -> userDaoJdbc2.add(user));
        assertThrows(DataIntegrityViolationException.class, () -> userDaoJdbc2.add(user));
    }

    @Test
    void sqlExceptionTranslate() {
        try {
            userDaoJdbc2.add(user);
            userDaoJdbc2.add(user);
        } catch (final DuplicateKeyException e) {
            final SQLException sqlException = (SQLException) e.getRootCause();
            final SQLErrorCodeSQLExceptionTranslator sqlExceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSourceBean);

            assertThat(sqlExceptionTranslator.translate(null, null, sqlException), instanceOf(DuplicateKeyException.class));
        }
    }

    @Test
    void update() {
        final User user4 = new User("whiteship", "백기선", "married", Level.BASIC, 1, 0, "test@gmail.com");
        final User user5 = new User("whiteship2", "백기선", "married", Level.BASIC, 1, 0, "test@gmail.com");
        userDaoJdbc2.add(user4);
        userDaoJdbc2.add(user5);

        user4.setLogin(50);
        int updateRows = userDaoJdbc2.update(user4);
        assertEquals(1, updateRows);

        final User getUser4 = userDaoJdbc2.get(user4.getId());
        assertEquals(user4, getUser4);

        final User getUser5 = userDaoJdbc2.get(user5.getId());
        assertEquals(getUser5, user5);
    }

    @Test
    void test() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String asdf = "asdf";
        final Class<? extends String> aClass = asdf.getClass();
        final Method length = aClass.getMethod("length");
        assertEquals(length.invoke(asdf), asdf.length());
    }
}