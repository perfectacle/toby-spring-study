package example.domain;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class UppercaseAdvice implements MethodInterceptor {
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final String returnValue = (String) invocation.proceed();
        return returnValue.toUpperCase();
    }
}
