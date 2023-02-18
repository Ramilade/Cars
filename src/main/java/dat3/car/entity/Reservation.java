package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "username")
    private Member member;

    @CreationTimestamp
    private LocalDate reservationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;

    public Reservation(Car car, Member member, LocalDate rentalDate) {
        this.car = car;
        this.member = member;
        this.rentalDate = rentalDate;
    }

}
