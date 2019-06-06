package example.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/testApplicationContext.xml")
class ApplicationContextTest {
    @Autowired
    private ApplicationContext context;

    private static Set<ApplicationContextTest> testObjects = new HashSet<>();
    private static ApplicationContext contextObject = null;

    @Test
    void test1() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);

        assertThat(contextObject == null || contextObject == this.context, is(true));
        contextObject = this.context;
    }

    @Test
    void test2() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);

        assertTrue(contextObject == null || contextObject == this.context);
        contextObject = this.context;
    }

    @Test
    void test3() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);

        assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
        contextObject = this.context;
    }
}