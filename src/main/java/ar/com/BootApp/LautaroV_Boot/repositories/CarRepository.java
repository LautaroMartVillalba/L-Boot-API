package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.car.CarEntity;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    List<CarEntity> findByModelContaining(String model);
    List<CarEntity> findByCompany(CarCompany company);
    List<CarEntity> findByColour(CarColors colour);
    List<CarEntity> findByDoors(int doorsQ);
    @Query("SELECT c FROM CarEntity c WHERE c.price >= :min and c.price <= :max")
    List<CarEntity> findByPriceBetween(double min, double max);
    List<CarEntity> findByTraction4x4True();
    List<CarEntity> findByTraction4x4False();
    Optional<CarEntity> findByModelContainingAndCompany(String model, CarCompany company);
    List<CarEntity> findByModelContainingAndColour(String model, CarColors colour);
    List<CarEntity> findByCompanyAndPriceBetween(CarCompany company, double min, double max);
    List<CarEntity> findByCompanyAndTraction4x4True(CarCompany company);
    List<CarEntity> findByCompanyAndTraction4x4False(CarCompany company);
}
