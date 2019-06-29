package example.object.ch01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicketOffice {
    // 판매 누적 금액인가?? 아니면 돈통에 들어있는 현금?
    private long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(final long amount, final Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public void sellTicketTo(final Audience audience) {
        final Ticket ticket = getTicket();
        final long ticketFee = audience.buy(ticket);
        plusAmount(ticketFee);
    }

    private Ticket getTicket() {
        return tickets.remove(0);
    }

    private void minusAmount(final long amount) {
        this.amount -= amount;
    }

    private void plusAmount(final long amount) {
        this.amount += amount;
    }
}
