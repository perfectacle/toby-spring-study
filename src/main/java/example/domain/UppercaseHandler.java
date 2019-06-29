package example.domain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
    private final Object target;

    public UppercaseHandler(final Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final Object returnValue = method.invoke(target, args);
        if(returnValue instanceof String) {
            return ((String) returnValue).toUpperCase();
        }
        return returnValue;
    }
}
