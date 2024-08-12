package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.tool.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    List<Tool> findByNameContaining(String name);
    List<Tool> findByCompanyContaining(String company);
    List<Tool> findByPriceBetween(double min, double max);
    List<Tool> findByAvailableTrue();
    List<Tool> findByAvailableFalse();
    List<Tool> findByNameContainingAndCompanyContaining(String name, String company);
    List<Tool> findByNameContainingAndPriceBetween(String name, double min, double max);
    List<Tool> findByCompanyContainingAndPriceBetween(String company, double min, double max);
    Optional<Tool> findByCompanyAndNameAndPriceBetween(String company, String name, double min, double max);

}
