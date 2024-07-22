package ar.com.BootApp.LautaroV_Boot.repositories.book;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitle(String title);

    List<Book> findByAuthorContaining(String author);

    List<Book> findByGender(String upperCase);

    Optional<Book> findByTitleAndAuthor(String title, String author);

    List<Book> findByTitleAndGender(String title, String gender);

    List<Book> findByAuthorAndGender(String author, String gender);

    @Query("SELECT b FROM Book b WHERE b.pages >= :min AND b.pages <= :max")
    List<Book> findByPagesBetween(int min, int max);

    @Query("SELECT b FROM Book b WHERE b.price >= :min and b.price <= :max")
    List<Book> findByPriceBetween(int min, int max);

    @Query("SELECT b FROM Book b WHERE b.author = :author and b.available = TRUE")
    List<Book> findByAuthorAndAvailableTrue(String author);

    @Query("SELECT b FROM Book b WHERE b.author = :author and b.available = FALSE")
    List<Book> findByAuthorAndAvailableFalse(String author);
}
