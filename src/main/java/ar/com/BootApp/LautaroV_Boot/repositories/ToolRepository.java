package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.tool.ToolEntiy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<ToolEntiy, Long> {

    Page<ToolEntiy> findByNameContaining(String name, PageRequest request);
    Page<ToolEntiy> findByCompanyContaining(String company, PageRequest request);
    Page<ToolEntiy> findByPriceBetween(double min, double max, PageRequest request);
    Page<ToolEntiy> findByAvailableTrue(PageRequest request);
    Page<ToolEntiy> findByAvailableFalse(PageRequest request);
    Page<ToolEntiy> findByNameContainingAndCompanyContaining(String name, String company, PageRequest request);
    Page<ToolEntiy> findByNameContainingAndPriceBetween(String name, double min, double max, PageRequest request);
    Page<ToolEntiy> findByCompanyContainingAndPriceBetween(String company, double min, double max, PageRequest request);
    Optional<ToolEntiy> findByCompanyAndNameAndPriceBetween(String company, String name, double min, double max, PageRequest request);
    Optional<ToolEntiy> findByCompanyAndNameAndPriceBetween(String company, String name, double min, double max);

}
