package cinema;

public class Available_Seat extends Seat {
    private int price;

    public Available_Seat(int row, int column){
        super(row, column);
        this.price = row <= 4 ? 10: 8;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
