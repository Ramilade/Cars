package dat3.car.repository;

import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    List<Car> getCarByBrandAndModel(String carBrand, String carModel);

    @Query("SELECT c FROM Car c WHERE NOT EXISTS (SELECT r.car FROM Reservation r WHERE r.car = c)")
    List<Car> findAllNotReserved();
}
