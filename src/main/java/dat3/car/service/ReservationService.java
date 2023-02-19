package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.security.entity.UserWithRoles;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;


    public ReservationService(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(ReservationRequest reservationRequest) {
        Car car = carRepository.findById(reservationRequest.getCarId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No car with that id"));
        LocalDate rentalDate = reservationRequest.getRentalDate();
        Member member = memberRepository.findById(reservationRequest.getMemberId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No member with that id"));

        if (reservationRepository.existsByCarAndRentalDate(car, rentalDate)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car is already reserved for that date");
        } else if (rentalDate.isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date is in the past");
        } else {
            Reservation reservation = new Reservation(car, member, reservationRequest.getRentalDate());
            reservationRepository.save(reservation);
            return reservation;
        }
    }


    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(ReservationResponse::new).toList();

    }

    public ReservationResponse getReservation(int reservation_id) {
        return reservationRepository.findById(reservation_id).map(ReservationResponse::new).orElse(null);
    }
}
