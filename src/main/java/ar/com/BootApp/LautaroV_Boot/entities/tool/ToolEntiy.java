package ar.com.BootApp.LautaroV_Boot.entities.tool;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "entity_tools")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ToolEntiy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String company;
    private double price;
    private boolean available;

}
