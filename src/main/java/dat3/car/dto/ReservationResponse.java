package dat3.car.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

    private int reservationId;
    private int Id;
    private String brand;
    private String model;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;


    public ReservationResponse(Reservation r) {
        this.reservationId = r.getId();
        this.Id = r.getCar().getCarId();
        this.brand = r.getCar().getBrand();
        this.model = r.getCar().getModel();
        this.rentalDate = r.getRentalDate();

    }

}