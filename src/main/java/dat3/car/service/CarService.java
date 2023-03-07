package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public void deleteCarById(int carId) {
        Car car = carRepository.findById(carId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        carRepository.delete(car);
    }

    public CarResponse addCar(CarRequest body) {
        Car car = CarRequest.getCarEntity(body);
        carRepository.save(car);
        return new CarResponse(car, false);
    }

    public List<CarResponse> getCars(boolean b) {
        List<Car> cars = carRepository.findAll();
        List<CarResponse> carResponses = cars.stream()
                .map(car -> new CarResponse(car, b)).toList();
        return carResponses;
    }

    public ResponseEntity<Boolean> editCar(CarRequest body, int carId) {
        Car car = carRepository.findById(carId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return new ResponseEntity(HttpStatus.OK);
    }

    public CarResponse getCarById(int id) {
        Car car = carRepository.getReferenceById(id);
        return new CarResponse(car, false);
    }


    public List<CarResponse> getCarByBrandAndModel(String carBrand, String carModel) {
        List<Car> car = carRepository.getCarByBrandAndModel(carBrand, carModel);
        List<CarResponse> carResponses = car.stream()
                .map(c -> new CarResponse(c, false)).toList();
        return carResponses;
    }

    public Double getAveragePrice() {
        int carSum = carRepository.findAll().size();
        double totalSum = carRepository.findAll().stream().mapToDouble(Car::getPricePrDay).sum();
        return totalSum / carSum;
    }

    public List<CarResponse> getAvailableCars() {
        List<Car> cars = carRepository.findAllNotReserved();
        List<CarResponse> carResponses = cars.stream()
                .map(car -> new CarResponse(car, false)).toList();
        return carResponses;
    }
}
