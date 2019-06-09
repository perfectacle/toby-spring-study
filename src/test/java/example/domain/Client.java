package example.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class, UserDao.class})
//@ContextConfiguration(locations = "/applicationContext.xml")
class Client {
    @Autowired
    private UserDao userDao;

    private static User user;
    private static User user2;
    private static User user3;

    @BeforeAll
    static void setupAll() {
        user = new User("whiteship", "백기선", "married");
        user2 = new User("whiteship2", "백기선", "married");
        user3 = new User("whiteship3", "백기선", "married");
    }

    @BeforeEach
    void setup() throws SQLException {
        userDao.deleteAll();
    }

    @Test
    void testAdd() throws SQLException {
        userDao.add(user);

        assertEquals(1, userDao.getCount());
    }

    @Test
    void testGet() throws SQLException {
        userDao.add(user);
        userDao.add(user2);

        User user3 = userDao.get(user.getId());
        User user4 = userDao.get(user2.getId());

        assertEquals(user.getId(), user3.getId());
        assertEquals(user.getName(), user3.getName());
        assertEquals(user.getPassword(), user3.getPassword());

        assertEquals(user2.getId(), user4.getId());
        assertEquals(user2.getName(), user4.getName());
        assertEquals(user2.getPassword(), user4.getPassword());
    }

    @Test
    void testGetEmpty() throws SQLException {
        assertEquals(0, userDao.getCount());

        assertThrows(EmptyResultDataAccessException.class, () -> userDao.get(""));
    }

    @Test
    void countingAndDeleteTest() throws SQLException {
        assertEquals(0, userDao.getCount());

        userDao.add(user);
        assertEquals(1, userDao.getCount());

        userDao.add(user2);
        assertEquals(2, userDao.getCount());

        userDao.add(user3);
        assertEquals(3, userDao.getCount());

        userDao.deleteAll();

        assertEquals(0, userDao.getCount());
    }

    @Test
    void getAllTest() {
        List<User> all = userDao.getAll();
        assertEquals(0, all.size());

        userDao.add(user3);
        all = userDao.getAll();
        assertEquals(1, all.size());
        assertEquals(user3, all.get(0));

        userDao.add(user2);
        all = userDao.getAll();
        assertEquals(2, all.size());
        assertEquals(user2, all.get(0));
        assertEquals(user3, all.get(1));

        userDao.add(user);
        all = userDao.getAll();
        assertEquals(3, all.size());
        assertEquals(user, all.get(0));
        assertEquals(user2, all.get(1));
        assertEquals(user3, all.get(2));
    }
//
//    @Test
//    void countingTest() throws SQLException {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
//        userDao userDao = context.getBean("userDao", userDao.class);
//
//        userDao.get("whiteship");
//        userDao.get("whiteship");
//        userDao.get("whiteship");
//
//        CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        CountingConnectionMaker connectionMaker2 = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        assertEquals(3, connectionMaker.getCounter());
//    }
}