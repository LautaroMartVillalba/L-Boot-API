package ar.com.BootApp.LautaroV_Boot.entities.book;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Setter
@Getter
@ToString
/**
 * This entity represent a Book in the DataBase
 */
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 50)
    private String author;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BookGenders gender;
    @Column(nullable = false)
    private int pages;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
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
