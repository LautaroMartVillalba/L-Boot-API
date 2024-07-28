package ar.com.BootApp.LautaroV_Boot.repositories.book;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContaining(String title);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByGender(BookGenders gender);

    List<Book> findByTitleContainingAndAuthorContaining(String title, String author);

    List<Book> findByTitleContainingAndGender(String title, BookGenders gender);

    List<Book> findByAuthorContainingAndGender(String author, BookGenders gender);

    @Query("SELECT b FROM Book b WHERE b.pages >= :min AND b.pages <= :max")
    List<Book> findByPagesBetween(int min, int max);

    @Query("SELECT b FROM Book b WHERE b.price >= :min and b.price <= :max")
    List<Book> findByPriceBetween(int min, int max);

    List<Book> findByAuthorContainingAndAvailableTrue(String author);

    List<Book> findByAuthorContainingAndAvailableFalse(String author);
}
