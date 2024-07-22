package ar.com.BootApp.LautaroV_Boot.entities.book;

import jakarta.persistence.*;
import lombok.*;

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
    @Enumerated(value = EnumType.STRING)
    private BookGenders gender;
    private int pages;
    private double price;
    private boolean available;


    public Book(Long id, String title, String author, BookGenders gender, int pages, double price, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.gender = gender;
        this.pages = pages;
        this.price = price;
        this.available = available;
    }
}
