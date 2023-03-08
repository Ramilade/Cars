package dat3.car.repository;

import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String>{

  boolean existsByEmail(String email);

  @Query("SELECT DISTINCT r.member FROM Reservation r")
  List<Member> findAllWithReservations();


}
