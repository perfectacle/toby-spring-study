package example.domain;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;

@Configuration
public class TxProxyFactoryBean {
    private final UserService userService;
    private final Pointcut pointcut;
    private final Advice advice;

    public TxProxyFactoryBean(final UserService userServiceImpl,
                              final Pointcut transactionPointcut,
                              final Advice transactionAdvice) {
        this.userService = userServiceImpl;
        this.pointcut = transactionPointcut;
        this.advice = transactionAdvice;
    }

    @Bean
    public UserService userService() {
        final ProxyFactoryBean pf = new ProxyFactoryBean();
        pf.setTarget(userService);
        pf.addAdvisor(new DefaultPointcutAdvisor(pointcut, advice));

        return (UserService) pf.getObject();
    }
}
