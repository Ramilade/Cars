package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    int id;
    String brand;
    String model;
    double pricePrDay;
    double bestDiscount;
    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    LocalDate created;

    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    LocalDate edited;

    //convert Car Entity to Car DTO
    public CarResponse(Car c, boolean includeAll) {
        this.id = c.getId();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        this.bestDiscount = c.getBestDiscount();
        if(includeAll){
            this.created = c.getCreated();
            this.edited = c.getLastEdited();
        }
    }
}
