package ar.com.BootApp.LautaroV_Boot.repositories.tool;

import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolKind;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolKindRepository extends JpaRepository<ToolKind, Long> {
}
