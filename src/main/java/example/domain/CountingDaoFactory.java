//package example.domain;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//public class CountingDaoFactory {
//    @Bean
//    public UserDao userDao(ConnectionMaker connectionMaker) {
//        return new UserDao(connectionMaker);
//    }
//
//    @Bean
//    public ConnectionMaker connectionMaker(ConnectionMaker realConnectionMaker) {
//        return new CountingConnectionMaker(realConnectionMaker);
//    }
//
//    @Bean
//    public ConnectionMaker realConnectionMaker() {
//        return new DConnectionMaker();
//    }
//}
