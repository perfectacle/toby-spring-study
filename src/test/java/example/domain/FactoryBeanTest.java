//package example.domain;
//
//import org.aopalliance.intercept.MethodInterceptor;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.aop.Pointcut;
//import org.springframework.aop.framework.ProxyFactoryBean;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {MessageFactoryBean.class, UppercaseAdvice.class, AdvisorConfig.class})
//class FactoryBeanTest {
//    @Autowired
//    private Message message;
//
//    @Autowired
//    private ApplicationContext context;
//
//    @Autowired
//    private MethodInterceptor uppercaseAdvice;
//
//    @Autowired
//    private Pointcut somePointcut;
//
//    @Test
//    void test() throws Exception {
//        final String text = "Factory Bean";
//
//        assertEquals(Message.class, this.message.getClass());
//        assertEquals(text, this.message.getText());
//
//        final Message message = context.getBean("message", Message.class);
//        assertEquals(Message.class, message.getClass());
//        assertEquals(text, message.getText());
//        assertNotSame(this.message, message);
//
//        final MessageFactoryBean factoryBean = context.getBean("&message", MessageFactoryBean.class);
//        final Message messageObject = factoryBean.getObject();
//        assertEquals(Message.class, messageObject.getClass());
//        assertEquals(text, messageObject.getText());
//        assertNotSame(this.message, messageObject);
//        assertNotSame(message, messageObject);
//
//
////        messageFactoryBean.setText(text);
//
////        final Message object = messageFactoryBean.getObject();
////        assertEquals(Message.class, object.getClass());
////        assertEquals(text, object.getText());
//
////        final Object message2 = context.getBean("messageFactoryBean");
////        assertEquals(Message.class, message2.getClass());
////        assertEquals(text, ((Message)message2).getText());
////        assertNotSame(object, message2);
//    }
//
//    @Test
//    void simpleProxy() {
//        final ProxyFactoryBean pf = new ProxyFactoryBean();
//        pf.setTarget(new HelloTarget());
//        pf.addAdvisor(new DefaultPointcutAdvisor(somePointcut, uppercaseAdvice));
//
//        final Hello hello = (Hello) pf.getObject();
//        assertEquals("HI TOBY", hello.sayHi("Toby"));
//        assertEquals("HELLO TOBY", hello.sayHello("Toby"));
//        assertEquals("Thank You Toby", hello.sayThankYou("Toby"));
//    }
//}
