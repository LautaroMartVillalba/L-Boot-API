package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.tool.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Long> {

    List<Tool> findByName(String name);
    List<Tool> findByCompany(String company);
    List<Tool> findByPriceBetween(double min, double max);
    List<Tool> findByAvailableTrue();
    List<Tool> findByAvailableFalse();
    List<Tool> findByNameAndCompany(String name, String company);
    List<Tool> findByNameAndPriceBetween(String name, double min, double max);
    List<Tool> findByCompanyAndPriceBetween(String company, double min, double max);

}
