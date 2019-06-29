package example.domain;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.util.PatternMatchUtils;

public class NameMatchClassPointcut extends NameMatchMethodPointcut {
    public void setMappedClassName(final String mappedClassName) {
        this.setClassFilter(new SimpleClassFilter(mappedClassName));
    }

    static class SimpleClassFilter implements ClassFilter {
        private String mappedName;

        SimpleClassFilter(final String mappedName) {
            this.mappedName = mappedName;
        }

        @Override
        public boolean matches(final Class<?> clazz) {
            return PatternMatchUtils.simpleMatch(mappedName, clazz.getSimpleName());
        }
    }
}
