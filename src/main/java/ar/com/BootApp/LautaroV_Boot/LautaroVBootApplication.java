package ar.com.BootApp.LautaroV_Boot;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookEntity;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.entities.user.privileges.PrivilegesEntity;
import ar.com.BootApp.LautaroV_Boot.entities.user.privileges.PrivilegesEnum;
import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEntity;
import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEnum;
import ar.com.BootApp.LautaroV_Boot.entities.user.user.UserEntity;
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

import java.util.List;
import java.util.Set;


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
