//package example.domain;
//
//import org.aopalliance.aop.Advice;
//import org.springframework.aop.Advisor;
//import org.springframework.aop.Pointcut;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AdvisorConfig {
//    @Bean
//    public Advisor transactionAdvisor(final Pointcut transactionPointcut, final Advice transactionInterceptor) {
//        return new DefaultPointcutAdvisor(transactionPointcut, transactionInterceptor);
//    }
//}
