package example.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleProxyTest {
    @Test
    void simpleProxy() {
        final Hello hello = new HelloTarget();
        assertEquals("Hello Toby", hello.sayHello("Toby"));
        assertEquals("Hi Toby", hello.sayHi("Toby"));
        assertEquals("Thank You Toby", hello.sayThankYou("Toby"));

        final Hello dynamicProxy = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(),
                                                                 new Class[]{Hello.class},
                                                                 new UppercaseHandler(hello));
        assertEquals("HELLO TOBY", dynamicProxy.sayHello("Toby"));
        assertEquals("HI TOBY", dynamicProxy.sayHi("Toby"));
        assertEquals("THANK YOU TOBY", dynamicProxy.sayThankYou("Toby"));
    }
}
