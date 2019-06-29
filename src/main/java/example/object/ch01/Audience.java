package example.object.ch01;

public class Audience {
    private Bag bag;

    public Audience(final Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }

    public long buy(final Ticket ticket) {
        return bag.hold(ticket);
    }
}
