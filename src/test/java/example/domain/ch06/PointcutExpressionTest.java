package example.domain.ch06;

import example.domain.ComponentScanConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = ComponentScanConfig.class)
public class PointcutExpressionTest {
    @Test
    void methodSignaturePointcut() throws NoSuchMethodException {
        final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(public int example.domain.ch06.TargetInterfaceImpl.minus(int,int) throws java.lang.RuntimeException)");

        assertTrue(pointcut.getClassFilter().matches(TargetInterfaceImpl.class) &&
                           pointcut.getMethodMatcher().matches(TargetInterfaceImpl.class.getMethod("minus", int.class, int.class), null));

        assertFalse(pointcut.getClassFilter().matches(TargetInterfaceImpl.class) &&
                            pointcut.getMethodMatcher().matches(TargetInterfaceImpl.class.getMethod("plus", int.class, int.class), null));

        assertFalse(pointcut.getClassFilter().matches(Bean.class));
    }

    @Test
    void pointcutExpressionTest() throws NoSuchMethodException {
        targetClassPointcutMatches("execution(* *(..))", true, true, true, true, true, true);
    }

    private void targetClassPointcutMatches(String expression, boolean... expected) throws NoSuchMethodException {
        pointcutMatches(expression, expected[0], TargetInterfaceImpl.class, "hello");
        pointcutMatches(expression, expected[1], TargetInterfaceImpl.class, "hello", String.class);
        pointcutMatches(expression, expected[2], TargetInterfaceImpl.class, "plus", int.class, int.class);
        pointcutMatches(expression, expected[3], TargetInterfaceImpl.class, "minus", int.class, int.class);
        pointcutMatches(expression, expected[4], TargetInterfaceImpl.class, "method");
        pointcutMatches(expression, expected[5], Bean.class, "method");
    }

    private void pointcutMatches(String expression, boolean expected, Class<?> clazz, String methodName, Class<?>... args) throws NoSuchMethodException {
        final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);

        assertEquals(pointcut.getClassFilter().matches(clazz) &&
                             pointcut.getMethodMatcher().matches(clazz.getMethod(methodName, args), null),
                     expected);
    }
}
