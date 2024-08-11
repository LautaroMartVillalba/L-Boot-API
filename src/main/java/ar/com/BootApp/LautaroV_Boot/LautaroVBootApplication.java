package ar.com.BootApp.LautaroV_Boot;

import ar.com.BootApp.LautaroV_Boot.entities.tool.Tool;
import ar.com.BootApp.LautaroV_Boot.repositories.BookRepository;
import ar.com.BootApp.LautaroV_Boot.repositories.CarRepository;
import ar.com.BootApp.LautaroV_Boot.repositories.ToolRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class LautaroVBootApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LautaroVBootApplication.class, args);

		BookRepository bookRepo = context.getBean(BookRepository.class);
		CarRepository carRepo = context.getBean(CarRepository.class);
		ToolRepository toolRepo = context.getBean(ToolRepository.class);
//
//		for (int i = 1; i < 50; i++){
//			if(i <=10){
//				Book book = new Book((long)i, "Castle Of Thrones", "Mahito", BookGenders.FANTASY, 850, 890, "HinokamiKagura", true);
//				bookRepo.save(book);
//			}
//			if(i > 10 && i <=25){
//				Book book = new Book((long)i, "Master Of Puppets", "Rim Okumura", BookGenders.HISTORY, 320, 500, "Heracles", false);
//				bookRepo.save(book);
//			}
//			if(i > 25 && i <=37){
//				Book book = new Book((long)i, "Life In Forest", "Inosuke", BookGenders.SCIENCE, 450, 670, "Epicuro", true);
//				bookRepo.save(book);
//			}
//			if(i > 37){
//				Book book = new Book((long)i, "Titans Of Horizont", "Eren Jaeger", BookGenders.SCIENCE, 1350, 2030, "Maho", true);
//				bookRepo.save(book);
//			}
//		}

//		for (int i = 1; i < 53; i++){
//			if(i <=10){
//				Car car = new Car((long)i, "XZR-Torino", CarCompany.FORD, CarColors.BLACK, 4, 900000.00, false);
//				carRepo.save(car);
//			}
//			if(i > 10 && i <=25){
//				Car car = new Car((long)i, "XZR-Torino", CarCompany.FORD, CarColors.RED, 4, 800000.00, false);
//				carRepo.save(car);
//			}
//			if(i > 25 && i <=37) {
//				Car car = new Car((long)i, "Mahito-Course", CarCompany.FERRARI, CarColors.DARKBLUE, 2, 15000000.00, false);
//				carRepo.save(car);
//			}
//			if(i > 37 && i <50){
//				Car car = new Car((long)i, "Mars Maven", CarCompany.TESLA, CarColors.BLACK, 4, 8050000.00, false);
//				carRepo.save(car);
//			}if(i == 51){
//				Car car = new Car((long)i, "Over Tear", CarCompany.TESLA, CarColors.BLACK, 4, 8050000.00, false);
//				carRepo.save(car);
//			}if(i == 52){
//				Car car = new Car((long)i, "Super Tear", CarCompany.TESLA, CarColors.BLACK, 4, 8050000.00, true);
//				carRepo.save(car);
//			}
//		}
//	}
		for (int i = 1; i < 50; i++) {
			if (i <= 10) {
				Tool tool = new Tool((long) i, "Hammer", "Stalin", 250, true);
				toolRepo.save(tool);
			}
			if (i > 10 && i <= 25) {
				Tool tool = new Tool((long) i, "Saw", "Gradle", 710, true);
				toolRepo.save(tool);
			}
			if (i > 25 && i <= 37) {
				Tool tool = new Tool((long) i, "Screwdriver", "Stalin", 350, false);
				toolRepo.save(tool);
			}
			if (i > 37) {
				Tool tool = new Tool((long) i, "Tool Box", "Jakarta", 1200, true);
				toolRepo.save(tool);
			}
		}
	}
}
