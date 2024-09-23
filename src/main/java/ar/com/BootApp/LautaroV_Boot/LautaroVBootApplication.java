package ar.com.BootApp.LautaroV_Boot;

import ar.com.BootApp.LautaroV_Boot.entities.car.CarEntity;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import ar.com.BootApp.LautaroV_Boot.repositories.*;
import ar.com.BootApp.LautaroV_Boot.services.BookService;
import ar.com.BootApp.LautaroV_Boot.services.CarService;
import ar.com.BootApp.LautaroV_Boot.services.ToolService;
import ar.com.BootApp.LautaroV_Boot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LautaroVBootApplication {

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	UserService repository;
	@Autowired
	BookService bookService;
	@Autowired
	CarService carService;
	@Autowired
	ToolService toolService;
	@Autowired
	PrivilegesEntityRepository privilegesRepo;
	@Autowired
	RoleEntityRepository roleRepo;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LautaroVBootApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService repository, BookService bookService, CarService carService, ToolService toolService ,PrivilegesEntityRepository privilegesRepo, RoleEntityRepository roleRepo) {
		return args -> {



		};
	}
}
