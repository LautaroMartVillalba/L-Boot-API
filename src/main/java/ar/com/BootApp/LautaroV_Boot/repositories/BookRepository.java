package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookEntity;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByTitleContaining(String title);
    List<BookEntity> findByAuthorContaining(String author);
    List<BookEntity> findByGender(BookGenders gender);
    Optional<BookEntity> findByTitleContainingAndAuthorContaining(String title, String author);
    List<BookEntity> findByTitleContainingAndGender(String title, BookGenders gender);
    List<BookEntity> findByAuthorContainingAndGender(String author, BookGenders gender);
    @Query("SELECT b FROM BookEntity b WHERE b.pages >= :min AND b.pages <= :max")
    List<BookEntity> findByPagesBetween(int min, int max);
    @Query("SELECT b FROM BookEntity b WHERE b.price >= :min and b.price <= :max")
    List<BookEntity> findByPriceBetween(int min, int max);
    List<BookEntity> findByAuthorContainingAndAvailableTrue(String author);
    List<BookEntity> findByAuthorContainingAndAvailableFalse(String author);
}
