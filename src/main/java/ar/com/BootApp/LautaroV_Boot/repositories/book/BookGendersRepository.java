package ar.com.BootApp.LautaroV_Boot.repositories.book;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookGendersRepository extends JpaRepository<BookGenders, Long> {
}
