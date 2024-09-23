package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.car.CarEntity;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<CarEntity>> findAll(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "20") int size)
            throws EmptyDataBaseException {
        Page<CarEntity> list = service.findAllCars(page,size);
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
    public ResponseEntity<Page<CarEntity>> findByModel (@PathVariable String model,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByModel(model, page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company/CarCompany.{company}")
    public ResponseEntity<Page<CarEntity>> findByCompany (@PathVariable CarCompany company,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByCompany(company, page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-colour/CarColors.{color}")
    public ResponseEntity<Page<CarEntity>> findByColour(@PathVariable CarColors color,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByColour(color, page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-doors/{doors}")
    public ResponseEntity<Page<CarEntity>> findByDoors (@PathVariable int doors,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByDoors(doors, page, size);

        if (result.isEmpty() || doors < 2 || doors > 4){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-price/{min}/{max}")
    public ResponseEntity<Page<CarEntity>> findByPrice(@PathVariable double min,
                                                       @PathVariable double max,
                                                       @RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByPriceBetween(min, max, page, size);

        if (result.isEmpty() || min < 0 && max <= min){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-4x4")
    public ResponseEntity<Page<CarEntity>> findBy4x4True(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByTraction4x4True(page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-not-4x4")
    public ResponseEntity<Page<CarEntity>> findBy4x4False(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByTraction4x4False(page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model&company/{model}/CarCompany.{company}")
    public ResponseEntity<Page<CarEntity>> findByModelAndCar(@PathVariable String model,
                                                                 @PathVariable CarCompany company,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByModelAndCompany(model, company, page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-model&colour/{model}/CarColors.{color}")
    public ResponseEntity<Page<CarEntity>> findbyModelAndColour(@PathVariable String model,
                                                                @PathVariable CarColors color,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByModelAndColour(model, color, page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&price/CarCompany.{company}/{min}/{max}")
    public ResponseEntity<Page<CarEntity>> findByCompanyAndPrice(@PathVariable CarCompany company,
                                                                 @PathVariable double min,
                                                                 @PathVariable double max,
                                                                 @RequestParam(defaultValue = "0") int page,
                                                                 @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByCompanyAndPriceBetween(company, min, max, page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-company&4x4/CarCompany.{company}")
    public ResponseEntity<Page<CarEntity>> findByCompanyAnd4x4True(@PathVariable CarCompany company,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result  = service.findByCompanyAndTraction4x4True(company, page, size);

        if (result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by.company&not4x4/CarCompany.{company}")
    public ResponseEntity<Page<CarEntity>> findByCompanyAnd4x4False(@PathVariable CarCompany company,
                                                                    @RequestParam(defaultValue = "0") int page,
                                                                    @RequestParam(defaultValue = "20") int size){
        Page<CarEntity> result = service.findByCompanyAndTraction4x4False(company, page, size);

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