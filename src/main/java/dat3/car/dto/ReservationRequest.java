package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {

    private String memberId;
    private int carId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;

    public ReservationRequest(String member, int car, LocalDate rentalDate) {
        this.memberId = member;
        this.carId = car;
        this.rentalDate = rentalDate;
    }

}