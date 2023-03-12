package dat3.car.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;
    @ManyToOne
    private Car car;
    @ManyToOne
    private Member member;
    @CreationTimestamp
    private LocalDate reservationDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;


    public Reservation(Car car, Member member, LocalDate reservationDate, LocalDate rentalDate) {
        this.car = car;
        this.member = member;
        this.reservationDate = reservationDate;
        this.rentalDate = rentalDate;
    }

}


