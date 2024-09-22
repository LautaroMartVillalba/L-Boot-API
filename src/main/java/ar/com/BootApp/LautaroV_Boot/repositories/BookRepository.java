package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookEntity;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Page<BookEntity> findByTitleContaining(String title, PageRequest request);
    Page<BookEntity> findByAuthorContaining(String author, PageRequest request);
    Page<BookEntity> findByGender(BookGenders gender, PageRequest request);
    Page<BookEntity> findByTitleContainingAndAuthorContaining(String title, String author, PageRequest request);
    Optional<BookEntity> findByTitleContainingAndAuthorContaining(String title, String author);
    Page<BookEntity> findByTitleContainingAndGender(String title, BookGenders gender, PageRequest request);
    Page<BookEntity> findByAuthorContainingAndGender(String author, BookGenders gender, PageRequest request);
    @Query("SELECT b FROM BookEntity b WHERE b.pages >= :min AND b.pages <= :max")
    Page<BookEntity> findByPagesBetween(int min, int max, PageRequest request);
    @Query("SELECT b FROM BookEntity b WHERE b.price >= :min and b.price <= :max")
    Page<BookEntity> findByPriceBetween(int min, int max, PageRequest request);
    Page<BookEntity> findByAuthorContainingAndAvailableTrue(String author, PageRequest request);
    Page<BookEntity> findByAuthorContainingAndAvailableFalse(String author, PageRequest request);
}
