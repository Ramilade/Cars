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
import java.util.*;


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
    Member m3 = new Member("member3", passwordUsedByAll, "abc@def.dk", "Bob", "Doe", "Rosenvængets Allé 1", "Copenhagen", "2100");
    Member m4 = new Member("member4", passwordUsedByAll, "hello@world.dk", "Alice", "Wonderland", "Søndergade 10", "Odense", "5000");
    Member m5 = new Member("member5", passwordUsedByAll, "foo@bar.dk", "John", "Doe", "Hovedvejen 1", "Aarhus", "8000");
    Member m6 = new Member("member6", passwordUsedByAll, "test@test.dk", "Jane", "Doe", "Finsensvej 10", "Frederiksberg", "2000");
    Member m7 = new Member("member7", passwordUsedByAll, "foo@baz.dk", "Peter", "Parker", "Østerbrogade 120", "Copenhagen", "2100");
    Member m8 = new Member("member8", passwordUsedByAll, "bar@foo.dk", "Mary", "Jane", "Amagerbrogade 10", "Copenhagen", "2300");
    Member m9 = new Member("member9", passwordUsedByAll, "user@domain.dk", "Donald", "Duck", "Vesterbrogade 2", "Copenhagen", "1620");
    Member m10 = new Member("member10", passwordUsedByAll, "user2@domain.dk", "Mickey", "Mouse", "Hovedgaden 5", "Aalborg", "9000");
    Member m11 = new Member("member11", passwordUsedByAll, "user3@domain.dk", "Bugs", "Bunny", "Algade 10", "Roskilde", "4000");
    Member m12 = new Member("member12", passwordUsedByAll, "user4@domain.dk", "Scooby", "Doo", "Klampenborgvej 6", "Klampenborg", "2930");
    Member m13 = new Member("member13", passwordUsedByAll, "user5@domain.dk", "Tom", "Jerry", "Vimmelskaftet 32", "Copenhagen", "1160");
    Member m14 = new Member("member14", passwordUsedByAll, "user6@domain.dk", "Jerry", "Seinfeld", "Nyhavn 5", "Copenhagen", "1051");
    Member m15 = new Member("member15", passwordUsedByAll, "user7@domain.dk", "Elon", "Musk", "Kronprinsessegade 28", "Copenhagen", "1306");

    List<Member> members = Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15);
    memberRepository.saveAll(members);

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


    //memberRepository.save(m1);
    //memberRepository.save(m2);

    Car c1 = new Car("Audi","Q4",200.0, 10);
    Car c2 = new Car("Mazda", "6", 300.0, 15);
    Car c3 = new Car("BMW", "330e",400.0,5);
    Car c4 = new Car("Toyota", "Camry", 250.0, 12);
    Car c5 = new Car("Honda", "Accord", 280.0, 14);
    Car c6 = new Car("Ford", "Mustang", 350.0, 8);
    Car c7 = new Car("Chevrolet", "Camaro", 320.0, 9);
    Car c8 = new Car("Mercedes-Benz", "C-Class", 400.0, 6);
    Car c9 = new Car("Lexus", "ES", 280.0, 11);
    Car c10 = new Car("Kia", "Stinger", 340.0, 13);
    Car c11 = new Car("Hyundai", "Sonata", 260.0, 12);
    Car c12 = new Car("Nissan", "Altima", 270.0, 10);
    Car c13 = new Car("Subaru", "Legacy", 240.0, 14);
    Car c14 = new Car("Volvo", "S60", 320.0, 7);
    Car c15 = new Car("Acura", "TLX", 300.0, 11);

    List<Car> cars = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15);
    carRepository.saveAll(cars);

    /*
    carRepository.save(c1);
    carRepository.save(c2);
    carRepository.save(c3);
    carRepository.save(c1);
    carRepository.save(c2);
    carRepository.save(c3);
    carRepository.save(c4);
    carRepository.save(c5);
    carRepository.save(c6);
    carRepository.save(c7);
    carRepository.save(c8);
    carRepository.save(c9);
    carRepository.save(c10);
    carRepository.save(c11);
    carRepository.save(c12);
    carRepository.save(c13);
    carRepository.save(c14);
    carRepository.save(c15);
*/
    ReservationRequest reservationRequest1 = new ReservationRequest();
    reservationRequest1.setCarId(1);
    reservationRequest1.setMemberId("member1");
    reservationRequest1.setRentalDate(LocalDate.of(2023,2,28));

    ReservationRequest reservationRequest2 = new ReservationRequest();
    reservationRequest2.setCarId(2);
    reservationRequest2.setMemberId("member2");
    reservationRequest2.setRentalDate(LocalDate.of(2023,2,28));

    //reservationService.createReservation(reservationRequest1);
    //reservationService.createReservation(reservationRequest2);






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


