//package example.domain;
//
//import org.aopalliance.aop.Advice;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.interceptor.TransactionInterceptor;
//
//import java.util.Properties;
//
//@Configuration
//public class AdviceConfig {
//    @Bean
//    public Advice transactionInterceptor(final PlatformTransactionManager dataSourceTransactionManager) {
//        final Properties properties = new Properties();
////        properties.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
////        properties.setProperty("*", "PROPAGATION_REQUIRED");
//
//        return new TransactionInterceptor(dataSourceTransactionManager, properties);
//    }
//}
