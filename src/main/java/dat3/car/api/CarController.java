package dat3.car.api;


import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")

public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //MEMBER
    @GetMapping
    List<CarResponse> getCars(){
        return carService.getCars(false);
    }

    //MEMBER
    @GetMapping(path = "/{carId}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.getCarById(id, false);
    }

    //ANONYMOUS
    @PostMapping
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //ADMIN
    @PutMapping("/{carId}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int carId){
        return carService.editCar(body, carId);
    }

    //ADMIN
    @DeleteMapping("/{carId}")
    void deleteCarById(@PathVariable int carId){
        carService.deleteCarById(carId);
    }

    //ADMIN
    @PatchMapping("/bestDiscount/{carId}/{value}")
    void setBestDiscount(@PathVariable int carId, @PathVariable double value){
        carService.setBestDiscount(carId, value);
    }


}
