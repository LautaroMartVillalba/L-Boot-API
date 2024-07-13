package ar.com.BootApp.LautaroV_Boot.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

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
    /**
     * For convention; the schemas/tables by a relationship into two schemas will be built by:
     * {owner-class-name} + and + {second-class-name}
     */
    @ManyToMany
    @JoinTable(name = "tools-and-kinds",
                joinColumns = @JoinColumn(name = "toolID"),
                inverseJoinColumns = @JoinColumn(name = "kindID")
    )
    @ToString.Exclude
    private Set<ToolKind> kind;
    private boolean available;

    public Tool(Long id, String name, String company, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.price = price;
        this.kind = new HashSet<>();
        this.available = available;
    }
}
