package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(String name);

}
