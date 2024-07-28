package ar.com.BootApp.LautaroV_Boot.entities.car;

import ar.com.BootApp.LautaroV_Boot.entities.car.carEnums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.carEnums.CarCompany;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @Enumerated(EnumType.STRING)
    private CarCompany company;
    @Enumerated(EnumType.STRING)
    private CarColors colour;
    private int doors;
    private double price;
    private boolean traction4x4;

}
