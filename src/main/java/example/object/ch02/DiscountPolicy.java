package example.object.ch02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(final DiscountCondition... conditions) {
        this.conditions.addAll(Arrays.asList(conditions));
    }

    public Money calculateDiscountAmount(final Scereening screening) {
        for (final DiscountCondition condition : conditions) {
            if (condition.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }

        return Money.ZERO;
    }

    protected abstract Money getDiscountAmount(final Scereening screening);
}
