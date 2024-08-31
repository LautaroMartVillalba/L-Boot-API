package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEnum;
import ar.com.BootApp.LautaroV_Boot.entities.user.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserEntityByEmail(String email);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findUserEntityByName(String username);
    List<UserEntity> findByRole(RoleEnum role);
    List<UserEntity> findByNameAndRole(String email, RoleEnum role);

}
