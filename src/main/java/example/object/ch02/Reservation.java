package example.object.ch02;

public class Reservation {
    private Customer customer;
    private Scereening scereening;
    private Money fee;
    private int audienceCount;

    public Reservation(final Customer customer,
                       final Scereening scereening,
                       final Money fee,
                       final int audienceCount) {

        this.customer = customer;
        this.scereening = scereening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
