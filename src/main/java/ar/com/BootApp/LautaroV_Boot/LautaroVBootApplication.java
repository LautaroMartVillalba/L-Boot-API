package ar.com.BootApp.LautaroV_Boot;

import ar.com.BootApp.LautaroV_Boot.entities.book.BookEntity;
import ar.com.BootApp.LautaroV_Boot.entities.book.BookGenders;
import ar.com.BootApp.LautaroV_Boot.entities.car.CarEntity;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolEntiy;
import ar.com.BootApp.LautaroV_Boot.entities.user.privileges.PrivilegesEntity;
import ar.com.BootApp.LautaroV_Boot.entities.user.privileges.PrivilegesEnum;
import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEntity;
import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEnum;
import ar.com.BootApp.LautaroV_Boot.entities.user.UserEntity;
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
import java.util.Optional;
import java.util.Set;


@SpringBootApplication
public class LautaroVBootApplication {

	@Autowired
	PasswordEncoder encoder;
//	@Autowired
//	UserService repository;
//	@Autowired
//	BookService bookService;
//	@Autowired
//	CarService carService;
//	@Autowired
//	ToolService toolService;
//	@Autowired
//	PrivilegesEntityRepository privilegesRepo;
//	@Autowired
//	RoleEntityRepository roleRepo;

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LautaroVBootApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserService repository, BookService bookService, CarService carService, ToolService toolService ,PrivilegesEntityRepository privilegesRepo, RoleEntityRepository roleRepo) {
		return args -> {
			/*Privileges to roles*/
			PrivilegesEntity get = PrivilegesEntity.builder().name(PrivilegesEnum.GET).build();
			PrivilegesEntity delete = PrivilegesEntity.builder().name(PrivilegesEnum.DELETE).build();
			PrivilegesEntity head = PrivilegesEntity.builder().name(PrivilegesEnum.HEAD).build();
			PrivilegesEntity post = PrivilegesEntity.builder().name(PrivilegesEnum.POST).build();
			PrivilegesEntity put = PrivilegesEntity.builder().name(PrivilegesEnum.PUT).build();
			PrivilegesEntity patch = PrivilegesEntity.builder().name(PrivilegesEnum.PATCH).build();
			PrivilegesEntity option = PrivilegesEntity.builder().name(PrivilegesEnum.OPTIONS).build();

			privilegesRepo.saveAll(List.of(get, delete, head, post, put, patch, option));

			/*Roles to users*/
			RoleEntity roleDeveloper = RoleEntity.builder().roleName(RoleEnum.DEVELOPER).privileges(Set.of(
					get, delete, head, post, put, patch, option)).build();
			RoleEntity roleAdmin = RoleEntity.builder().roleName(RoleEnum.ADMIN).privileges(Set.of(
					get, delete, post, put, patch, option)).build();
			RoleEntity roleReader = RoleEntity.builder().roleName(RoleEnum.READER).privileges(Set.of(
					get, post, delete, patch)).build();
			RoleEntity roleDriver = RoleEntity.builder().roleName(RoleEnum.DRIVER).privileges(Set.of(
					get, post, delete, patch)).build();
			RoleEntity roleMechanic = RoleEntity.builder().roleName(RoleEnum.MECHANIC).privileges(Set.of(
					get, post, delete, patch)).build();

			roleRepo.saveAll(Set.of(roleDeveloper, roleAdmin, roleReader, roleDriver, roleMechanic));

			/*Persist users*/
			UserEntity admin = UserEntity.builder()
					.userName("Administrator")
					.email("admin@gmail.com")
					.password("admin")
					.role(Set.of(roleAdmin))
					.accountNoExpired(true)
					.credentialsNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true).build();

			UserEntity reader = UserEntity.builder()
					.userName("Reader")
					.email("reader@gmail.com")
					.password("reader")
					.role(Set.of(roleReader))
					.accountNoExpired(true)
					.credentialsNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true).build();

			UserEntity driver = UserEntity.builder()
					.userName("Driver")
					.email("driver@gmail.com")
					.password("driver")
					.role(Set.of(roleDriver))
					.accountNoExpired(true)
					.credentialsNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true).build();

			UserEntity mechanic = UserEntity.builder()
					.userName("Mechanic")
					.email("mechanic@gmail.com")
					.password("mechanic")
					.role(Set.of(roleMechanic))
					.accountNoExpired(true)
					.credentialsNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true).build();

			UserEntity developer = UserEntity.builder()
					.userName("Developer")
					.email("developer@gmail.com")
					.password("developer")
					.role(Set.of(roleDeveloper))
					.accountNoExpired(true)
					.credentialsNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true).build();

			UserEntity developer2 = UserEntity.builder()
					.userName("Developer")
					.email("developer2@gmail.com")
					.password("developer")
					.role(Set.of(roleDeveloper))
					.accountNoExpired(true)
					.credentialsNoExpired(true)
					.accountNoLocked(true)
					.isEnabled(true).build();

			repository.saveUser(admin);
			repository.saveUser(reader);
			repository.saveUser(mechanic);
			repository.saveUser(driver);
			repository.saveUser(developer);
			repository.saveUser(developer2);

			/*Books*/

			BookEntity bookOne = BookEntity.builder()
					.title("First")
					.author("Mahito")
					.pages(840)
					.price(8777)
					.available(true)
					.publisher("Gege")
					.gender(BookGenders.FANTASY).build();

			bookService.saveBook(bookOne);

		};
	}
}
