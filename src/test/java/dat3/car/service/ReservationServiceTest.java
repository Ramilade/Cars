package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
    }

   @Test
    void createReservationCarUnavailable() {
        //Create a test with a car that is already reserved
        Member m1 = new Member("member1", "1234", "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
        Car c1 = new Car("Audi", "Q4", 200.0, 10);
        memberRepository.save(m1);
        carRepository.save(c1);

        LocalDate rentalDate = LocalDate.of(2023, 2, 18);

        ReservationRequest reservationRequest = new ReservationRequest();
        reservationRequest.setCarId(c1.getCarId());
        reservationRequest.setMemberId(m1.getUsername());
        reservationRequest.setRentalDate(rentalDate);

        //Creates another reservation with the same car and rental date

Reservation existingReservation = new Reservation();
        existingReservation.setCar(c1);
        existingReservation.setMember(m1);
        existingReservation.setRentalDate(rentalDate);
        reservationRepository.save(existingReservation);

        //Try to create a reservation with the same car and rental date
        Reservation reservation = reservationService.createReservation(reservationRequest);

        //Assert that the reservation is null
        assertNull(reservation);

    }
}