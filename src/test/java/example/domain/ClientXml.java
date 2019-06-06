package example.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/applicationContext.xml")
class ClientXml {
    private static User user;
    private static User user2;
    private static User user3;

    @Autowired
    private ApplicationContext context;
    private UserDao dao;

    @BeforeAll
    static void setupAll() {
        user = new User("whiteship", "백기선", "married");
        user2 = new User("whiteship2", "백기선", "married");
        user3 = new User("whiteship3", "백기선", "married");
    }

    @BeforeEach
    void setup() throws SQLException {
        System.out.println(this);
        System.out.println(this.context);

        this.dao = context.getBean("userDaoDConnection", UserDao.class);
        this.dao.reset();
    }

    @Test
    void testAdd() throws SQLException {
        dao.add(user);

        assertEquals(1, dao.getCount());
    }

    @Test
    void testGet() throws SQLException {
        dao.add(user);
        dao.add(user2);

        User user3 = dao.get(user.getId());
        User user4 = dao.get(user2.getId());

        assertEquals(user.getId(), user3.getId());
        assertEquals(user.getName(), user3.getName());
        assertEquals(user.getPassword(), user3.getPassword());

        assertEquals(user2.getId(), user4.getId());
        assertEquals(user2.getName(), user4.getName());
        assertEquals(user2.getPassword(), user4.getPassword());
    }

    @Test
    void testGetEmpty() throws SQLException {
        dao.reset();
        assertEquals(0, dao.getCount());

        assertThrows(EmptyResultDataAccessException.class, () -> dao.get(""));
    }

    @Test
    void countingAndDeleteTest() throws SQLException {
        dao.reset();

        assertEquals(0, dao.getCount());

        dao.add(user);
        assertEquals(1, dao.getCount());

        dao.add(user2);
        assertEquals(2, dao.getCount());

        dao.add(user3);
        assertEquals(3, dao.getCount());

        dao.reset();

        assertEquals(0, dao.getCount());
    }
//
//    @Test
//    void countingTest() throws SQLException {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
//        UserDao dao = context.getBean("userDao", UserDao.class);
//
//        dao.get("whiteship");
//        dao.get("whiteship");
//        dao.get("whiteship");
//
//        CountingConnectionMaker connectionMaker = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        CountingConnectionMaker connectionMaker2 = context.getBean("connectionMaker", CountingConnectionMaker.class);
//        assertEquals(3, connectionMaker.getCounter());
//    }
}