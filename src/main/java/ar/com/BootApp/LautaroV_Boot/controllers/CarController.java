package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.car.Car;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.DuplicatedCarException;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.NullCarException;
import ar.com.BootApp.LautaroV_Boot.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private CarService service;

    /*---------------------Get Methods-----------------------*/
    @GetMapping("/all")
    public ResponseEntity<List<Car>> findAll() throws EmptyDataBaseException {
        List<Car> list = service.findAllCars();

        if (list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<Car>> findByID(@PathVariable Long id){
        Optional<Car> result = service.findByCarID(id);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model/{model}")
    public ResponseEntity<List<Car>> findByModel (@PathVariable String model){
        List<Car> result = service.findByModel(model);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company/CarCompany.{company}")
    public ResponseEntity<List<Car>> findByCompany (@PathVariable CarCompany company){
        List<Car> result = service.findByCompany(company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-colour/CarColors.{color}")
    public ResponseEntity<List<Car>> findByColour(@PathVariable CarColors color){
        List<Car> result = service.findByColour(color);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-doors/{doors}")
    public ResponseEntity<List<Car>> findByDoors (@PathVariable int doors){
        List<Car> result = service.findByDoors(doors);

        if (result.isEmpty() || doors < 2 || doors > 4){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-price/{min}/{max}")
    public ResponseEntity<List<Car>> findByPrice(@PathVariable double min, @PathVariable double max){
        List<Car> result = service.findByPriceBetween(min, max);

        if (result.isEmpty() || min < 0 && max <= min){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-4x4")
    public ResponseEntity<List<Car>> findBy4x4True(){
        List<Car> result = service.findByTraction4x4True();

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-not-4x4")
    public ResponseEntity<List<Car>> findBy4x4False(){
        List<Car> result = service.findByTraction4x4False();

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model&company/{model}/CarCompany.{company}")
    public ResponseEntity<Optional<Car>> findByModelAndCar(@PathVariable String model,@PathVariable CarCompany company){
        Optional<Car> result = service.findByModelAndCompany(model, company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model&colour/{model}/CarColors.{color}")
    public ResponseEntity<List<Car>> findbyModelAndColour(@PathVariable String model, @PathVariable CarColors color){
        List<Car> result = service.findByModelAndColour(model, color);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&price/CarCompany.{company}/{min}/{max}")
    public ResponseEntity<List<Car>> findByCompanyAndPrice(@PathVariable CarCompany company, @PathVariable double min, @PathVariable double max){
        List<Car> result = service.findByCompanyAndPriceBetween(company, min, max);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&4x4/CarCompany.{company}")
    public ResponseEntity<List<Car>> findByCompanyAnd4x4True(@PathVariable CarCompany company){
        List<Car> result  = service.findByCompanyAndTraction4x4True(company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by.company&not4x4/CarCompany.{company}")
    public ResponseEntity<List<Car>> findByCompanyAnd4x4False(@PathVariable CarCompany company){
        List<Car> result = service.findByCompanyAndTraction4x4False(company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    public ResponseEntity<Car> postCar(@RequestBody Car car) throws DuplicatedCarException, NullCarException {
        service.saveCar(car);
        return ResponseEntity.ok(car);
    }
}