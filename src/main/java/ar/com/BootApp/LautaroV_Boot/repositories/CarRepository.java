package ar.com.BootApp.LautaroV_Boot.repositories;

import ar.com.BootApp.LautaroV_Boot.entities.car.Car;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByModelContaining(String model);
    List<Car> findByCompany(CarCompany company);
    List<Car> findByColour(CarColors colour);
    List<Car> findByDoors(int doorsQ);
    @Query("SELECT c FROM Car c WHERE c.price >= :min and c.price <= :max")
    List<Car> findByPriceBetween(double min, double max);
    List<Car> findByTraction4x4True();
    List<Car> findByTraction4x4False();

    Optional<Car> findByModelContainingAndCompany(String model, CarCompany company);
    List<Car> findByModelContainingAndColour(String model, CarColors colour);
    List<Car> findByCompanyAndPriceBetween(CarCompany company, double min, double max);
    List<Car> findByCompanyAndTraction4x4True(CarCompany company);
    List<Car> findByCompanyAndTraction4x4False(CarCompany company);
}
