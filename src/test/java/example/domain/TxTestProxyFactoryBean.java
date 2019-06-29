//package example.domain;
//
//import org.aopalliance.aop.Advice;
//import org.springframework.aop.Pointcut;
//import org.springframework.aop.framework.ProxyFactoryBean;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TxTestProxyFactoryBean {
//    private final UserService userService;
//    private final Pointcut pointcut;
//    private final Advice advice;
//
//    public TxTestProxyFactoryBean(final TestUserServiceImpl testUserService,
//                                  final Pointcut transactionPointcut,
//                                  final Advice transactionAdvice) {
//        testUserService.setId("123");
//
//        this.userService = testUserService;
//        this.pointcut = transactionPointcut;
//        this.advice = transactionAdvice;
//    }
//
//    @Bean
//    public UserService userServiceTestProxy() {
//        final ProxyFactoryBean pf = new ProxyFactoryBean();
//        pf.setTarget(userService);
//        pf.addAdvisor(new DefaultPointcutAdvisor(pointcut, advice));
//
//        return (UserService) pf.getObject();
//    }
//}
