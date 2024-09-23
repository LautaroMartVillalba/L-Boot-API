package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.car.CarEntity;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Page<CarEntity> findByModelContaining(String model, PageRequest request);
    Page<CarEntity> findByCompany(CarCompany company, PageRequest request);
    Page<CarEntity> findByColour(CarColors colour, PageRequest request);
    Page<CarEntity> findByDoors(int doorsQ, PageRequest request);
    @Query("SELECT c FROM CarEntity c WHERE c.price >= :min and c.price <= :max")
    Page<CarEntity> findByPriceBetween(double min, double max, PageRequest request);
    Page<CarEntity> findByTraction4x4True(PageRequest request);
    Page<CarEntity> findByTraction4x4False(PageRequest request);
    Page<CarEntity> findByModelContainingAndCompany(String model, CarCompany company, PageRequest request);
    Optional<CarEntity> findByModelContainingAndCompany(String model, CarCompany company);
    Page<CarEntity> findByModelContainingAndColour(String model, CarColors colour, PageRequest request);
    Page<CarEntity> findByCompanyAndPriceBetween(CarCompany company, double min, double max, PageRequest request);
    Page<CarEntity> findByCompanyAndTraction4x4True(CarCompany company, PageRequest request);
    Page<CarEntity> findByCompanyAndTraction4x4False(CarCompany company, PageRequest request);
}
