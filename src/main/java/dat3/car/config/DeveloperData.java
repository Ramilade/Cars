package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class DeveloperData implements ApplicationRunner {
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  CarRepository carRepository;



  private final String passwordUsedByAll = "test12";

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

  }
}


