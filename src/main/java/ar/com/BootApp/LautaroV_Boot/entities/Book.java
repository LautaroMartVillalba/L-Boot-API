package ar.com.BootApp.LautaroV_Boot.entities;

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
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    @ManyToMany
    @JoinTable(name = "book-genders",
                joinColumns = @JoinColumn(name = "bookID"),
                inverseJoinColumns = @JoinColumn(name = "genderID")
    )
    @Enumerated(EnumType.STRING)
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
