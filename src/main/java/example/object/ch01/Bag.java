package example.object.ch01;

public class Bag {
    private long amount;
    private Invitation invitation;
    private Ticket ticket;

    public Bag(final long amount) {
        this.amount = amount;
    }

    public Bag(final Invitation invitation, final long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    public long hold(final Ticket ticket) {
        if(hasInvitation()) {
            this.ticket = ticket;
            return 0L;
        } else {
            this.ticket = ticket;
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }

    private boolean hasInvitation() {
        return invitation != null;
    }

    private boolean hasTicket() {
        return ticket != null;
    }

    private void setTicket(final Ticket ticket) {
        this.ticket = ticket;
    }

    private void minusAmount(final long amount) {
        this.amount -= amount;
    }

    private void plusAmount(final long amount) {
        this.amount += amount;
    }
}
