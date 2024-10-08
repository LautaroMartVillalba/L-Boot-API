package ar.com.BootApp.LautaroV_Boot.entities.car;

import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entity_cars")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String model;
    @Enumerated(value = EnumType.STRING)
    private CarCompany company;
    @Enumerated(value = EnumType.STRING)
    private CarColors colour;
    private int doors;
    private double price;
    private boolean traction4x4;

}
