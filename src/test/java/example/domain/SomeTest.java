package example.domain;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class SomeTest {
    @Test
    void test() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SomeContainer.class);
        context.getBean("someObject");
        context.getBean("someObjectOfObject");
    }

    @Test
    void test2() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SomeContainer2.class);
        context.getBean("someObject");
        context.getBean("someObjectOfObject");
    }

    @Test
    void test3() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SomeContainer3.class);
        context.getBean("someObject");
        context.getBean("someObjectOfObject");
    }

    @Test
    void test4() {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SomeContainer4.class);
        context.getBean("someObject");
        context.getBean("someObjectOfObject");
    }
}
