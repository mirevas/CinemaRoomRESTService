package cinema;

public class Return {
    private Available_Seat returned_ticket;

    public Return(Available_Seat available_Seat) {
        this.returned_ticket = available_Seat;
    }

    public Available_Seat getReturned_ticket() {
        return returned_ticket;
    }

    public void setReturned_ticket(Available_Seat returned_ticket) {
        this.returned_ticket = returned_ticket;
    }
}
