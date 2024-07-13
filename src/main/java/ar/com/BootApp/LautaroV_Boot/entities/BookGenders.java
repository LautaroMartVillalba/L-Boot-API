package ar.com.BootApp.LautaroV_Boot.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "books-enders-")

/**
 * This entity represents the gender that Books will have
 */
public class BookGenders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    @ManyToMany(mappedBy = "gender")
    @JsonIgnore
    private List<Book> titles;

    public BookGenders(Long id, String gender) {
        this.id = id;
        this.gender = gender;
    }
}