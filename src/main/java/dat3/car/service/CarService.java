package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<CarResponse> getCars(boolean includeAll) {
        List<Car> cars = carRepository.findAll();

        return cars.stream().map(c -> new CarResponse(c, includeAll)).toList();
    }
    public CarResponse getCarById(int id, boolean includeAll) throws Exception {
        Car car = carRepository.getReferenceById(id);
        return new CarResponse(car, includeAll);
    }

    public CarResponse addCar(CarRequest carRequest) {
        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, false);
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int carId) {
        Car updateCar = carRepository.getReferenceById(carId);
        updateCar.setId(body.getId());
        updateCar.setBrand(body.getBrand());
        updateCar.setModel(body.getModel());
        updateCar.setPricePrDay(body.getPricePrDay());

        carRepository.save(updateCar);
        return ResponseEntity.ok(true);
    }

    public void setBestDiscount(int id, double value) {
        Car car = carRepository.getReferenceById(id);
        car.setBestDiscount(value);
        carRepository.save(car);
    }

    public void deleteCarById(int id) {
        carRepository.deleteById(id);
    }
}
