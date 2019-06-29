package example.object.ch01;

public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(final TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    public void sellTo(final Audience audience) {
        ticketOffice.sellTicketTo(audience);
    }
}
