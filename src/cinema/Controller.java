package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Controller<seat1> {
    private int total_rows = 9;
    private int total_columns = 9;
    private List<Available_Seat> available_seats = getAvailable_seats(total_rows, total_columns);

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
    public ResponseEntity<Available_Seat> purchaseSeat(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        if (row < 0 || row > total_rows || column < 0 || column > total_columns) {
            return new ResponseEntity(Map.of("error", "The number of a row or a column is out of bounds!"), HttpStatus.BAD_REQUEST);
        } else {
            for (Available_Seat available_seat : available_seats) {
                if (available_seat.getRow() == seat.getRow() && available_seat.getColumn() == seat.getColumn()) {
                    available_seats.remove(available_seat);
                    return ResponseEntity.status(HttpStatus.OK).body(available_seat);
                }
            }
            return new ResponseEntity(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
        }
    }
}
