package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    ReservationRepository reservationRepository;
    CarRepository carRepository;
    MemberRepository memberRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            CarRepository carRepository,
            MemberRepository memberRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }

    public List<ReservationResponse> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(ReservationResponse::new)
                .toList();
    }

    public ReservationResponse getReservation(int reservation_id) {
        Reservation reservation = reservationRepository.findById(reservation_id)
                .orElseThrow();
        return new ReservationResponse(reservation);
    }

    public ReservationResponse makeReservation(ReservationRequest reservationRequest) {
        Car car = carRepository.findById(reservationRequest.getCarId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Car not found"));

        if (reservationRequest.getRentalDate().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Given date is in the past");
        }

        if (reservationRepository.existsByCarAndRentalDate(
                car, reservationRequest.getRentalDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Car is already reserved");
        }

        Member member = memberRepository.findById(reservationRequest.getUsername())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Member not found"));

        Reservation reservation = new Reservation(
                car, member, LocalDate.now(), reservationRequest.getRentalDate());

        reservationRepository.save(reservation);

        return new ReservationResponse(reservation);
    }

    public List<ReservationResponse> getReservationsByUsername(String username) {
        List<Reservation> reservations = reservationRepository.getReservationsByMember_Username(username);
        return reservations.stream()
                .map(ReservationResponse::new)
                .toList();
    }

}
