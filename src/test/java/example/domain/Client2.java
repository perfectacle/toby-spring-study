//package example.domain;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.jdbc.datasource.SimpleDriverDataSource;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = "/applicationContext.xml")
//class Client2 {
//    @Autowired
//    private UserDao UserDao;
//
//    private static User user;
//    private static User user2;
//    private static User user3;
//
//    @BeforeAll
//    static void setupAll() {
//        user = new User("whiteship", "백기선", "married");
//        user2 = new User("whiteship2", "백기선", "married");
//        user3 = new User("whiteship3", "백기선", "married");
//    }
//
//    @BeforeEach
//    void setup() throws SQLException {
//        UserDao.deleteAll();
//    }
//
//    @Test
//    void testAdd() throws SQLException {
//        UserDao.add(user);
//
//        assertEquals(1, UserDao.getCount());
//    }
//
//    @Test
//    void testGet() throws SQLException {
//        UserDao.add(user);
//        UserDao.add(user2);
//
//        User user3 = UserDao.get(user.getId());
//        User user4 = UserDao.get(user2.getId());
//
//        assertEquals(user.getId(), user3.getId());
//        assertEquals(user.getName(), user3.getName());
//        assertEquals(user.getPassword(), user3.getPassword());
//
//        assertEquals(user2.getId(), user4.getId());
//        assertEquals(user2.getName(), user4.getName());
//        assertEquals(user2.getPassword(), user4.getPassword());
//    }
//
//    @Test
//    void testGetEmpty() throws SQLException {
//        UserDao.deleteAll();
//        assertEquals(0, UserDao.getCount());
//
//        assertThrows(EmptyResultDataAccessException.class, () -> UserDao.get(""));
//    }
//
//    @Test
//    void countingAndDeleteTest() throws SQLException {
//        UserDao.deleteAll();
//
//        assertEquals(0, UserDao.getCount());
//
//        UserDao.add(user);
//        assertEquals(1, UserDao.getCount());
//
//        UserDao.add(user2);
//        assertEquals(2, UserDao.getCount());
//
//        UserDao.add(user3);
//        assertEquals(3, UserDao.getCount());
//
//        UserDao.deleteAll();
//
//        assertEquals(0, UserDao.getCount());
//    }
//}