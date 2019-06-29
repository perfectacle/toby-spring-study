package example.object.ch02;

import java.time.Duration;

public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(final String title,
                 final Duration runningTime,
                 final Money fee,
                 final DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return this.fee;
    }

    public Money calculateMovieFee(final Scereening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
