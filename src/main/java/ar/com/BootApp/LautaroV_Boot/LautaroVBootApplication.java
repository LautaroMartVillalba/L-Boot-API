package ar.com.BootApp.LautaroV_Boot;

import ar.com.BootApp.LautaroV_Boot.entities.book.Book;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.service.BookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LautaroVBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LautaroVBootApplication.class, args);

		BookService service = context.getBean(BookService.class);

		for (int i = 1; i < 50; i++){
			if(i >= 0 && i <=10){
				Book book = new Book((long)i, "Castle Of Thrones", "Mahito", BookGenders.FANTASY, 850, 890, true);
				service.saveBook(book);
			}
			if(i > 10 && i <=25){
				Book book = new Book((long)i, "Master Of Puppets", "Rim Okumura", BookGenders.HISTORY, 320, 500, false);
				service.saveBook(book);
			}
			if(i > 25 && i <=37){
				Book book = new Book((long)i, "Life In Forest", "Inosuke", BookGenders.SCIENCE, 450, 670, true);
				service.saveBook(book);
			}
			if(i > 37 && i <= 51){
				Book book = new Book((long)i, "Titans Of Horizont", "Eren Jaeger", BookGenders.SCIENCE, 1350, 2030, true);
				service.saveBook(book);
			}
		}
	}

}
