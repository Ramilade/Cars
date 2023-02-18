package dat3.car.dto;

import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class CarRequest {

    int CarId;
    String brand;
    String model;
    double pricePrDay;
    double bestDiscount;

    public static Car getCarEntity(CarRequest c) {
        return new Car(c.CarId, c.brand, c.model, c.pricePrDay, c.bestDiscount);
    }
    public CarRequest(Car c) {
        this.CarId = c.getCarId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
    }

    public CarRequest(String brand, String model, double pricePrDay, double bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }


}



