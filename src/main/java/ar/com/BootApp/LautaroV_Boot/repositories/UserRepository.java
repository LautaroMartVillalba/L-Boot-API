package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.user.User;
import ar.com.BootApp.LautaroV_Boot.entities.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByRole(UserRole role);
    List<User> findByNameAndRole(String email, UserRole role);

}
