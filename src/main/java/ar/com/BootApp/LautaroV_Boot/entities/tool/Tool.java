package ar.com.BootApp.LautaroV_Boot.entities.tool;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
/**
 * This entity represent a Tool in the DataBase
 */
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String company;
    private double price;
    private boolean available;

    public Tool(Long id, String name, String company, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
        this.available = available;
    }
}
