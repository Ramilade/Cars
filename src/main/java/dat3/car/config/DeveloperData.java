package dat3.car.config;

import dat3.car.dto.ReservationRequest;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import dat3.car.service.ReservationService;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@EnableJpaRepositories(basePackages = {"dat3.security.repository", "dat3.car.repository"})
//@ComponentScan(basePackages = "dat3.security")
@Configuration
public class DeveloperData implements ApplicationRunner {

  @Autowired
  MemberRepository memberRepository;
  @Autowired
  CarRepository carRepository;
  @Autowired
  UserWithRolesRepository userWithRolesRepository;
  @Autowired
  ReservationRepository reservationRepository;

  @Autowired
    ReservationService reservationService = new ReservationService(carRepository, memberRepository, reservationRepository);



  final String passwordUsedByAll = "test12";



  @Override
  public void run(ApplicationArguments args) throws Exception {
    Member m1 = new Member("member1", passwordUsedByAll, "memb1@a.dk", "Kurt", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");
    Member m2 = new Member("member2", passwordUsedByAll, "aaa@dd.dk", "Hanne", "Wonnegut", "Lyngbyvej 2", "Lyngby", "2800");


    List<String> favColor1 = new ArrayList<>();
    favColor1.add("Blue");
    favColor1.add("White");

    List<String> favColor2 = new ArrayList<>();
    favColor2.add("Black");
    favColor2.add("Yellow");

    m1.setFavoriteCarColors(favColor1);
    m2.setFavoriteCarColors(favColor2);

    Map<String,String> phones = new HashMap<>();


    phones.put("mobile", "12345678");
    phones.put("work", "87654321");

    m1.setPhones(phones);
    m2.setPhones(phones);


    memberRepository.save(m1);
    memberRepository.save(m2);

    Car c1 = new Car("Audi","Q4",200.0, 10);
    Car c2 = new Car("Mazda", "6", 300.0, 15);
    Car c3 = new Car("BMW", "330e",400.0,5);

    carRepository.save(c1);
    carRepository.save(c2);
    carRepository.save(c3);

    ReservationRequest reservationRequest1 = new ReservationRequest();
    reservationRequest1.setCarId(1);
    reservationRequest1.setMemberId("member1");
    reservationRequest1.setRentalDate(LocalDate.of(2023,2,18));

    ReservationRequest reservationRequest2 = new ReservationRequest();
    reservationRequest2.setCarId(2);
    reservationRequest2.setMemberId("member2");
    reservationRequest2.setRentalDate(LocalDate.of(2023,2,18));

    reservationService.createReservation(reservationRequest1);
    reservationService.createReservation(reservationRequest2);






    setupUserWithRoleUsers();

  }




  /*****************************************************************************************
   NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
   iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
   *****************************************************************************************/

  private void setupUserWithRoleUsers() {

    System.out.println("******************************************************************************");
    System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
    System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
    System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
    System.out.println("******************************************************************************");
    dat3.security.entity.UserWithRoles user1 = new dat3.security.entity.UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
    dat3.security.entity.UserWithRoles user2 = new dat3.security.entity.UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
    dat3.security.entity.UserWithRoles user3 = new dat3.security.entity.UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
    dat3.security.entity.UserWithRoles user4 = new dat3.security.entity.UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
    user1.addRole(dat3.security.entity.Role.USER);
    user1.addRole(dat3.security.entity.Role.ADMIN);
    user2.addRole(dat3.security.entity.Role.USER);
    user3.addRole(dat3.security.entity.Role.ADMIN);
    //No Role assigned to user4
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(user4);
  }



}


