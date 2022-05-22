package cinema;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class Purchase{
    private UUID token;
    private Available_Seat ticket;

    public Purchase(Available_Seat available_Seat) {
        this.token = randomUUID();
        this.ticket = available_Seat;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Available_Seat getTicket() {
        return ticket;
    }

    public void setTicket(Available_Seat ticket) {
        this.ticket = ticket;
    }
}
