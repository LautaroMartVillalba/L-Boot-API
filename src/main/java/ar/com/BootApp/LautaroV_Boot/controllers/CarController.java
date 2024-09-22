package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.car.CarEntity;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
@PreAuthorize("hasAnyRole('ROLE_DRIVER', 'ROLE_ADMIN', 'ROLE_DEVELOPER')")
public class CarController {

    private CarService service;

    /*---------------------Get Methods-----------------------*/
    @GetMapping("/all")
    public ResponseEntity<List<CarEntity>> findAll() throws EmptyDataBaseException {
        List<CarEntity> list = service.findAllCars();
        if (list.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<CarEntity>> findByID(@PathVariable Long id){
        Optional<CarEntity> result = service.findByCarID(id);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model/{model}")
    public ResponseEntity<List<CarEntity>> findByModel (@PathVariable String model){
        List<CarEntity> result = service.findByModel(model);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company/CarCompany.{company}")
    public ResponseEntity<List<CarEntity>> findByCompany (@PathVariable CarCompany company){
        List<CarEntity> result = service.findByCompany(company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-colour/CarColors.{color}")
    public ResponseEntity<List<CarEntity>> findByColour(@PathVariable CarColors color){
        List<CarEntity> result = service.findByColour(color);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-doors/{doors}")
    public ResponseEntity<List<CarEntity>> findByDoors (@PathVariable int doors){
        List<CarEntity> result = service.findByDoors(doors);

        if (result.isEmpty() || doors < 2 || doors > 4){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-price/{min}/{max}")
    public ResponseEntity<List<CarEntity>> findByPrice(@PathVariable double min, @PathVariable double max){
        List<CarEntity> result = service.findByPriceBetween(min, max);

        if (result.isEmpty() || min < 0 && max <= min){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-4x4")
    public ResponseEntity<List<CarEntity>> findBy4x4True(){
        List<CarEntity> result = service.findByTraction4x4True();

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-not-4x4")
    public ResponseEntity<List<CarEntity>> findBy4x4False(){
        List<CarEntity> result = service.findByTraction4x4False();

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model&company/{model}/CarCompany.{company}")
    public ResponseEntity<Optional<CarEntity>> findByModelAndCar(@PathVariable String model,@PathVariable CarCompany company){
        Optional<CarEntity> result = service.findByModelAndCompany(model, company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model&colour/{model}/CarColors.{color}")
    public ResponseEntity<List<CarEntity>> findbyModelAndColour(@PathVariable String model, @PathVariable CarColors color){
        List<CarEntity> result = service.findByModelAndColour(model, color);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&price/CarCompany.{company}/{min}/{max}")
    public ResponseEntity<List<CarEntity>> findByCompanyAndPrice(@PathVariable CarCompany company, @PathVariable double min, @PathVariable double max){
        List<CarEntity> result = service.findByCompanyAndPriceBetween(company, min, max);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&4x4/CarCompany.{company}")
    public ResponseEntity<List<CarEntity>> findByCompanyAnd4x4True(@PathVariable CarCompany company){
        List<CarEntity> result  = service.findByCompanyAndTraction4x4True(company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by.company&not4x4/CarCompany.{company}")
    public ResponseEntity<List<CarEntity>> findByCompanyAnd4x4False(@PathVariable CarCompany company){
        List<CarEntity> result = service.findByCompanyAndTraction4x4False(company);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    public ResponseEntity<CarEntity> postCar(@RequestBody CarEntity car) throws ExistingObjectException, NullObjectException {
        service.saveCar(car);
        return ResponseEntity.ok(car);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
    public ResponseEntity<CarEntity> deleteCar(@PathVariable Long id){
        boolean result = service.deleteCarByID(id);
        if (result){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}