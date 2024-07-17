package ar.com.BootApp.LautaroV_Boot.entities.book;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
@ToString
/**
 * This entity represent a Book in the DataBase
 */
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    /**
     * For convention; the schemas/tables by a relationship into two schemas will be built by:
     * {owner-class-name} + and + {second-class-name}
     */
    @ManyToMany
    @JoinTable(
            name = "books-and-genders",
            joinColumns = @JoinColumn(name = "bookID"),
            inverseJoinColumns = @JoinColumn(name = "genderID")
    )
    @ToString.Exclude
    private Set<BookGenders> gender;

    private int pages;
    private double price;
    private boolean available;


    public Book(Long id, String title, String author, int pages, double price, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.gender = new HashSet<>();
        this.pages = pages;
        this.price = price;
        this.available = available;
    }
}
