package ar.com.BootApp.LautaroV_Boot.repositories.car;

import ar.com.BootApp.LautaroV_Boot.entities.car.Car;
import ar.com.BootApp.LautaroV_Boot.entities.car.carEnums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.carEnums.CarCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
//    List<Car> findByModel(String model);
//    List<Car> findByCompany(CarCompany company);
//    List<Car> findByColour(CarColors colour);
//    List<Car> findByDoors(int doorsQ);
//    List<Car> findByPriceBetween(double min, double max);
//    List<Car> findByTraction4x4();
//    List<Car> findByNotTraction4x4();
//
//    Car findByModelAndCompany(String model, CarCompany company);
//    List<Car> findByModelAndColour(String model, CarColors colour);
//    List<Car> findByModelAndDoors(String model, int doors);
//    List<Car> findByCompanyAndPriceBetween(CarCompany company, double min, double max);
//    List<Car> findByCompanyAndTraction4x4(CarCompany company);

}
