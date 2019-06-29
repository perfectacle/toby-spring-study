package example.domain;

import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointcutTest {
    @Test
    void name() {
        final NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut() {
            @Override
            public ClassFilter getClassFilter() { // 클래스 필터링
                return clazz -> clazz.getSimpleName().startsWith("HelloT");
            }
        };

        // 메서드 필터링
        pointcut.setMappedName("sayH*");

        checkAdviced(new HelloTarget(), pointcut, true);
        checkAdviced(new HelloArmy(), pointcut, false);
    }

    private void checkAdviced(Object target, Pointcut pointcut, boolean adviced) {
        final ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(target);
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, new UppercaseAdvice()));
        final Hello proxy = (Hello) proxyFactoryBean.getObject();

        if(adviced) {
            assertEquals("HELLO TOBY", proxy.sayHello("Toby"));
            assertEquals("HI TOBY", proxy.sayHi("Toby"));
            assertEquals("Thank You Toby", proxy.sayThankYou("Toby"));
        } else {
            assertEquals("Hello Toby", proxy.sayHello("Toby"));
            assertEquals("Hi Toby", proxy.sayHi("Toby"));
            assertEquals("Thank You Toby", proxy.sayThankYou("Toby"));
        }
    }
}
