package example.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JUnitTest {
    private static Set<JUnitTest> testObjects = new HashSet<>();

    @Test
    void test1() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);
    }

    @Test
    void test2() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);
    }

    @Test
    void test3() {
        assertFalse(testObjects.contains(this));
        testObjects.add(this);
    }
}
