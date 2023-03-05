package dat3.car.api;


import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
@CrossOrigin
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
    @GetMapping("/{id}")
    CarResponse getCarById(@PathVariable int id) throws Exception {
        return carService.getCarById(id);
    }

    //ANONYMOUS
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }

    //ADMIN
    @PutMapping("/{Id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int carId){
        return carService.editCar(body, carId);
    }

    //ADMIN
    @DeleteMapping("/{Id}")
    void deleteCarById(@PathVariable int carId){
        carService.deleteCarById(carId);
    }


}
