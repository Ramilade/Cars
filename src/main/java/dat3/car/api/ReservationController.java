package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.service.ReservationService;
import dat3.car.entity.Reservation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
@CrossOrigin
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationResponse> getReservations() {
        return reservationService.getReservations();
    }

    @GetMapping("/{reservation_id}")
    public ReservationResponse getReservation(@PathVariable int reservation_id) {
        return reservationService.getReservation(reservation_id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ReservationResponse createReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.makeReservation(reservationRequest);
    }

    @GetMapping("/member/{username}")
    public List<ReservationResponse> getReservationsByUsername(@PathVariable String username) {
        return reservationService.getReservationsByUsername(username);
    }
}
