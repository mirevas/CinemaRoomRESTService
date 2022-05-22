package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Controller<seat1> {
    private int total_rows = 9;
    private int total_columns = 9;
    private List<Available_Seat> available_seats = getAvailable_seats(total_rows, total_columns);
    private List<Purchase> purchased_seats = new ArrayList<>();

    public List<Available_Seat> getAvailable_seats(int total_rows, int total_columns){
        List<Available_Seat> list = new ArrayList<>();
        for (int i = 1; i <= total_rows; i++){
            for (int j = 1; j <= total_columns; j++){
                list.add(new Available_Seat(i, j));
            }
        }
        return list;
    }

    Seats seats = new Seats(total_rows, total_columns, available_seats);

    @GetMapping("/seats")
    public Seats getSeats(){
        return seats;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        if (row < 0 || row > total_rows || column < 0 || column > total_columns) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else {
            for (Available_Seat available_seat : available_seats) {
                if (available_seat.getRow() == seat.getRow() && available_seat.getColumn() == seat.getColumn()) {
                    available_seats.remove(available_seat);
                    Purchase purchase = new Purchase(available_seat);
                    purchased_seats.add(purchase);
                    return ResponseEntity.status(HttpStatus.OK).body(purchase);
                }
            }
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnSeat(@RequestBody Token token) {
        for (Purchase purchased_seat : purchased_seats) {
            if (purchased_seat.getToken().equals(token.getToken())){
                int row = purchased_seat.getTicket().getRow();
                int column = purchased_seat.getTicket().getColumn();
                purchased_seats.remove(purchased_seat);
                Available_Seat available_seat = new Available_Seat(row, column);
                available_seats.add(available_seat);
                Return re = new Return(available_seat);
                return ResponseEntity.status(HttpStatus.OK).body(re);
            }
        }
        return new ResponseEntity(Map.of("error", "Wrong token!"), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> stats(@RequestParam Map map) {
        if (!map.containsKey("password") || !map.get("password").equals("super_secret")){
            return new ResponseEntity(Map.of("error", "The password is wrong!"), HttpStatus.UNAUTHORIZED);
        } else {
            Statistics stats = new Statistics(available_seats, purchased_seats);
            return ResponseEntity.status(HttpStatus.OK).body(stats);
        }
    }
}
