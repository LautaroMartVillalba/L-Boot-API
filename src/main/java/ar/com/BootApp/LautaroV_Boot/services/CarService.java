package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.car.CarEntity;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository repository;

    /**
     *  Validate the Car's info to know if all data was received.
     * @param car Car object.
     * @return False if any parameter of 'car' was null. True if all is correct.
     */
    private boolean validateCar(CarEntity car) {
        return !car.getModel().isEmpty() && !(car.getPrice() < 1.00) && car.getColour() != null && car.getDoors() >= 3 && car.getCompany() != null;
    }

    /*--------------Default Methods--------------*/

    /**
     * Search and return a List of all cars in DataBase.
     * @return List af all cars in DataBase List if it has some registers.
     * @throws EmptyDataBaseException If it can't find any registers.
     */
    public Page<CarEntity> findAllCars(int page, int size) throws EmptyDataBaseException {
        PageRequest request = PageRequest.of(page, size);
        Page<CarEntity> result = repository.findAll(request);
        if (result.isEmpty()){
            throw new EmptyDataBaseException();
        }
        return result;
    }

    /**
     * Search a car in DataBase by his ID number (long type).
     * @param id Car's identification.
     * @return Optional of car if it can find a register. Optional.Empty if it can't.
     */
    public Optional<CarEntity> findByCarID(Long id) {
        return repository.findById(id);
    }

    /**
     *  Save a car in DataBase, using a first validation by all car's parameters.
     * @param car Car Object to persist in DataBase.
     * @throws NullObjectException If one of the car's parameters it's null.
     * @throws ExistingObjectException If the car's model name, colour and company match with one in DataBase.
     */
    public void saveCar(CarEntity car) throws NullObjectException, ExistingObjectException {
        if (!validateCar(car)) {
            throw new NullObjectException();
        }
        Optional<CarEntity> carRepo = repository.findByModelContainingAndCompany(car.getModel(), car.getCompany());
        if (carRepo.isPresent()) {
            CarEntity carOb = carRepo.get();
            if (carOb.getCompany() == car.getCompany() && Objects.equals(carOb.getModel(), car.getModel()) && carOb.getColour() == car.getColour()) {
                throw new ExistingObjectException();
            }
        }
        repository.save(car);
    }

    /**
     *  Search one car in DataBase witch matches with one existing. If it can find, delete the found car.
     * @param id Car's identification number (long type).
     * @return False if it can't find a car's id witch matches in DataBase. True if it can find and delete.
     */
    public boolean deleteCarByID(Long id) {
        Optional<CarEntity> carFind = repository.findById(id);
        if (carFind.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }
    /*-------------------Custom Methods-------------------*/

    /**
     *  Search and return a car by his Model Name.
     * @param model Car's model name.
     * @return One Optional of cars witch matches in DataBase.
     */
    public Page<CarEntity> findByModel(String model, int page, int size){
        if(model != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByModelContaining(model, request);
        }
        return Page.empty();
    }

    /**
     *  Search and return a car's List by is Company.
     * @param company CarCompany enum.
     * @return A List of cars if company exists like an enum (will be empty if it doesn't have saved cars).
     * Empty array if company enum type doesn't exist.
     */
    public Page<CarEntity> findByCompany(CarCompany company, int page, int size){
        if(company != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByCompany(company, request);
        }
        return Page.empty();
    }

    /**
     *  Search and return a car's List witch matches with insert color.
     * @param color CarColor enum.
     * @return A List of cars if color exists like an enum (will be empty if it doesn't have saved cars).
     * Empty array if color enum type doesn't exist.
     */
    public Page<CarEntity> findByColour(CarColors color, int page, int size){
        if(color != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByColour(color, request);
        }
        return Page.empty();
    }

    /**
     *  Search and return a List of cars by the insert number of doors.
     * @param doorsNumber Number of doors.
     * @return A List of Cars if doorsNumber is equals to 2 or 4 and if exists registers in DataBase.
     * Empty array if doorsNumber != 2 or 4, or if it can't find registers int DataBase.
     */
    public Page<CarEntity> findByDoors(int doorsNumber, int page, int size){
        if(doorsNumber == 2 || doorsNumber == 4){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByDoors(doorsNumber, request);
        }
        return Page.empty();
    }

    /**
     *  Search and return a List of cars witch price matches between the min and max values in DataBase.
     * @param min Minimum price.
     * @param max Maximum price.
     * @return A List of cars if min and max are valid values and the DataBase has registers witch
     * price matches.
     */
    public Page<CarEntity> findByPriceBetween(double min, double max, int page, int size){
        if (min > 0 && max > min){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByPriceBetween(min, max, request);
        }
        return Page.empty();
    }

    /**
     * Search and return a List of cars witch has 4x4 traction.
     * @return A List of cars.
     */
    public Page<CarEntity> findByTraction4x4True(int page, int size){
        PageRequest request = PageRequest.of(page, size);
        return repository.findByTraction4x4True(request);
    }

    /**
     * Search and return a List of cars witch doesn't have 4x4 traction.
     * @return A List of cars.
     */
    public Page<CarEntity> findByTraction4x4False(int page, int size){
        PageRequest request = PageRequest.of(page, size);
        return repository.findByTraction4x4False(request);
    }

    /**
     * Search and return a List of cars witch matches with a model name and company name if DataBase.
     * @param model Car's model name.
     * @param company Car's manufacturer company.
     * @return An Optional of car if model name and company manufacturer matches with one register in
     * DataBase. Else return an empty List.
     */
    public Optional<CarEntity> findByModelAndCompany(String model, CarCompany company){
        if(model != null && company != null){
            return repository.findByModelContainingAndCompany(model, company);
        }
        return Optional.empty();
    }

    /**
     * Search and return a List of cars witch matches with a model name and company name if DataBase.
     * @param model Car's model name.
     * @param company Car's manufacturer company.
     * @return An Optional of car if model name and company manufacturer matches with one register in
     * DataBase. Else return an empty List.
     */
    public Page<CarEntity> findByModelAndCompany(String model, CarCompany company, int page, int size){
        if(model != null && company != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByModelContainingAndCompany(model, company, request);
        }
        return Page.empty();
    }

    /**
     * search and return a List of cars witch matched with registers in DataBase.
     * @param model Car's model name.
     * @param color Car's color.
     * @return A List of cars if model name and colour matches with one register in Database. Else return
     * an empty List.
     */
    public Page<CarEntity> findByModelAndColour(String model, CarColors color, int page, int size){
        if(model != null && color != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByModelContainingAndColour(model, color, request);
        }
        return Page.empty();
    }

    /**
     *  Search and return a List of cars witch matches with registers in DataBase by manufacturer and a price range.
     * @param company Car's manufacturer company.
     * @param min Minimum price.
     * @param max Maximum price.
     * @return A List of cars if manufacturer and price matches with register in DataBase. Else return an empty List.
     */
    public Page<CarEntity> findByCompanyAndPriceBetween(CarCompany company, double min, double max, int page, int size){
        if(company != null && min > 0 && max > min){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByCompanyAndPriceBetween(company, min, max, request);
        }
        return Page.empty();
    }

    /**
     *  Search and return a List of cars witch has 4x4 traction in DataBase.
     * @param company Car's manufacturer company.
     * @return A List of cars if it can found registers in DataBase.
     * Return an empty List if it can't found registers.
     */
    public Page<CarEntity> findByCompanyAndTraction4x4True(CarCompany company, int page, int size){
        if(company != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByCompanyAndTraction4x4True(company, request);
        }
        return Page.empty();
    }

    /**
     *  Search and return a List of cars witch doesn't have 4x4 traction in DataBase.
     * @param company Car's manufacturer company.
     * @return A List of cars if it can found registers in DataBase.
     * Return an empty List if it can't found registers.
     */
    public Page<CarEntity> findByCompanyAndTraction4x4False(CarCompany company, int page, int size){
        if(company != null){
            PageRequest request = PageRequest.of(page, size);
            return repository.findByCompanyAndTraction4x4False(company, request);
        }
        return Page.empty();
    }

}
