package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolEntiy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntiy, Long> {

    List<ToolEntiy> findByNameContaining(String name);
    List<ToolEntiy> findByCompanyContaining(String company);
    List<ToolEntiy> findByPriceBetween(double min, double max);
    List<ToolEntiy> findByAvailableTrue();
    List<ToolEntiy> findByAvailableFalse();
    List<ToolEntiy> findByNameContainingAndCompanyContaining(String name, String company);
    List<ToolEntiy> findByNameContainingAndPriceBetween(String name, double min, double max);
    List<ToolEntiy> findByCompanyContainingAndPriceBetween(String company, double min, double max);
    Optional<ToolEntiy> findByCompanyAndNameAndPriceBetween(String company, String name, double min, double max);

}
