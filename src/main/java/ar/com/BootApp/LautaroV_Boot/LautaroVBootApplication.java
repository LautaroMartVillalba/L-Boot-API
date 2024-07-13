package ar.com.BootApp.LautaroV_Boot;

import ar.com.BootApp.LautaroV_Boot.entities.Book;
import ar.com.BootApp.LautaroV_Boot.repositories.BookGendersRepository;
import ar.com.BootApp.LautaroV_Boot.repositories.BookRepository;
import ar.com.BootApp.LautaroV_Boot.entities.BookGenders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LautaroVBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LautaroVBootApplication.class, args);
		BookRepository booksRep = context.getBean(BookRepository.class);
		BookGendersRepository gendersRep = context.getBean(BookGendersRepository.class);

		BookGenders gender = new BookGenders(null, "Fiction");
		BookGenders gender2 = new BookGenders(null, "Adventure");
		gendersRep.save(gender);
		gendersRep.save(gender2);

		Book book = new Book(null, "Makehim a Men", "Marina", 300, 230, true);
		Book book2 = new Book(null, "Second Book", "Julia", 130, 80, true);
		book.getGender().add(gender);
		book.getGender().add(gender2);
		booksRep.save(book);
		booksRep.save(book2);
	}

}
