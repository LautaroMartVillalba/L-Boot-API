package ar.com.BootApp.LautaroV_Boot;

import ar.com.BootApp.LautaroV_Boot.entities.Book;
import ar.com.BootApp.LautaroV_Boot.entities.Tool;
import ar.com.BootApp.LautaroV_Boot.entities.ToolKind;
import ar.com.BootApp.LautaroV_Boot.repositories.BookGendersRepository;
import ar.com.BootApp.LautaroV_Boot.repositories.BookRepository;
import ar.com.BootApp.LautaroV_Boot.entities.BookGenders;
import ar.com.BootApp.LautaroV_Boot.repositories.ToolKindRepository;
import ar.com.BootApp.LautaroV_Boot.repositories.ToolRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LautaroVBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LautaroVBootApplication.class, args);
		BookRepository booksRep = context.getBean(BookRepository.class);
		BookGendersRepository gendersRep = context.getBean(BookGendersRepository.class);
		ToolRepository toolRep = context.getBean(ToolRepository.class);
		ToolKindRepository toolKindRep = context.getBean(ToolKindRepository.class);

		//fast test of books
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
		System.out.println("books");
		System.out.println(booksRep.findAll());

		//fast test of tools
		Tool tool = new Tool(null, "hammer", "Stalin", 200, false);
		ToolKind kind = new ToolKind(null, "Construction");
		toolKindRep.save(kind);
		tool.getKind().add(kind);
		toolRep.save(tool);
		System.out.println("tools");
		System.out.println(toolRep.findAll());
	}

}
