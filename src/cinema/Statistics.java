package cinema;

import java.util.List;

public class Statistics {
    private int current_income;
    private int number_of_available_seats;
    private int number_of_purchased_tickets;

    public Statistics(List<Available_Seat> available_seats, List<Purchase> purchased_seats) {
        this.number_of_available_seats = available_seats.size();
        this.number_of_purchased_tickets = purchased_seats.size();
        int income = 0;
        for (Purchase purchase : purchased_seats) {
            income += purchase.getTicket().getPrice();
        }
        this.current_income = income;
    }

    public int getCurrent_income() {
        return current_income;
    }

    public void setCurrent_income(int current_income) {
        this.current_income = current_income;
    }

    public int getNumber_of_available_seats() {
        return number_of_available_seats;
    }

    public void setNumber_of_available_seats(int number_of_available_seats) {
        this.number_of_available_seats = number_of_available_seats;
    }

    public int getNumber_of_purchased_tickets() {
        return number_of_purchased_tickets;
    }

    public void setNumber_of_purchased_tickets(int number_of_purchased_tickets) {
        this.number_of_purchased_tickets = number_of_purchased_tickets;
    }
}
