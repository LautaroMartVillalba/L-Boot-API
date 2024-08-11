package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.car.Car;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarColors;
import ar.com.BootApp.LautaroV_Boot.entities.car.enums.CarCompany;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.DuplicatedCarException;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.EmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.exceptions.car.types.NullCarException;
import ar.com.BootApp.LautaroV_Boot.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private boolean validateCar(Car car) {
        if (car.getModel().isEmpty() || car.getPrice() < 1.00 || car.getColour() == null || car.getDoors() < 3 || car.getCompany() == null) {
            return false;
        }
        return true;
    }

    /*--------------Default Methods--------------*/

    /**
     * Search and return a List of all cars in DataBase.
     * @return List af all cars in DataBase List if it has some registers.
     * @throws EmptyDataBaseException If it can't find any registers.
     */
    public List<Car> findAllCars() throws EmptyDataBaseException {
        List<Car> result = repository.findAll();
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
    public Optional<Car> findByCarID(Long id) {
        return repository.findById(id);
    }

    /**
     *  Save a car in DataBase, using a first validation by all car's parameters.
     * @param car Car Object to persist in DataBase.
     * @throws NullCarException If one of the car's parameters it's null.
     * @throws DuplicatedCarException If the car's model name, colour and company match with one in DataBase.
     */
    public void saveCar(Car car) throws DuplicatedCarException, NullCarException {
        if (!validateCar(car)) {
            throw new NullCarException();
        }
        Optional<Car> carRepo = repository.findByModelContainingAndCompany(car.getModel(), car.getCompany());
        if (carRepo.isPresent()) {
            Car carOb = carRepo.get();
            if (carOb.getCompany() == car.getCompany() && Objects.equals(carOb.getModel(), car.getModel()) && carOb.getColour() == car.getColour()) {
                throw new DuplicatedCarException();
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
        Optional<Car> carFind = repository.findById(id);
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
    public List<Car> findByModel(String model){
        if(model != null){
            return repository.findByModelContaining(model);
        }
        return new ArrayList<>();
    }

    /**
     *  Search and return a car's List by is Company.
     * @param company CarCompany enum.
     * @return A List of cars if company exists like an enum (will be empty if it doesn't have saved cars).
     * Empty array if company enum type doesn't exist.
     */
    public List<Car> findByCompany(CarCompany company){
        if(company != null){
            return repository.findByCompany(company);
        }
        return new ArrayList<>();
    }

    /**
     *  Search and return a car's List witch matches with insert color.
     * @param color CarColor enum.
     * @return A List of cars if color exists like an enum (will be empty if it doesn't have saved cars).
     * Empty array if color enum type doesn't exist.
     */
    public List<Car> findByColour(CarColors color){
        if(color != null){
            return repository.findByColour(color);
        }
        return new ArrayList<>();
    }

    /**
     *  Search and return a List of cars by the insert number of doors.
     * @param doorsNumber Number of doors.
     * @return A List of Cars if doorsNumber is equals to 2 or 4 and if exists registers in DataBase.
     * Empty array if doorsNumber != 2 or 4, or if it can't find registers int DataBase.
     */
    public List<Car> findByDoors(int doorsNumber){
        if(doorsNumber == 2 || doorsNumber == 4){
            return repository.findByDoors(doorsNumber);
        }
        return new ArrayList<>();
    }

    /**
     *  Search and return a List of cars witch price matches between the min and max values in DataBase.
     * @param min Minimum price.
     * @param max Maximum price.
     * @return A List of cars if min and max are valid values and the DataBase has registers witch
     * price matches.
     */
    public List<Car> findByPriceBetween(double min, double max){
        if (min > 0 && max > min){
            return repository.findByPriceBetween(min, max);
        }
        return new ArrayList<>();
    }

    /**
     * Search and return a List of cars witch has 4x4 traction.
     * @return A List of cars.
     */
    public List<Car> findByTraction4x4True(){
        return repository.findByTraction4x4True();
    }

    /**
     * Search and return a List of cars witch doesn't have 4x4 traction.
     * @return A List of cars.
     */
    public List<Car> findByTraction4x4False(){
        return repository.findByTraction4x4False();
    }

    /**
     * Search and return a List of cars witch matches with a model name and company name if DataBase.
     * @param model Car's model name.
     * @param company Car's manufacturer company.
     * @return An Optional of car if model name and company manufacturer matches with one register in
     * DataBase. Else return an empty List.
     */
    public Optional<Car> findByModelAndCompany(String model, CarCompany company){
        if(model != null && company != null){
            return repository.findByModelContainingAndCompany(model, company);
        }
        return null;
    }

    /**
     * search and return a List of cars witch matched with registers in DataBase.
     * @param model Car's model name.
     * @param color Car's color.
     * @return A List of cars if model name and colour matches with one register in Database. Else return
     * an empty List.
     */
    public List<Car> findByModelAndColour(String model, CarColors color){
        if(model != null && color != null){
            return repository.findByModelContainingAndColour(model, color);
        }
        return new ArrayList<>();
    }

    /**
     *  Search and return a List of cars witch matches with registers in DataBase by manufacturer and a price range.
     * @param company Car's manufacturer company.
     * @param min Minimum price.
     * @param max Maximum price.
     * @return A List of cars if manufacturer and price matches with register in DataBase. Else return an empty List.
     */
    public List<Car> findByCompanyAndPriceBetween(CarCompany company, double min, double max){
        if(company != null && min > 0 && max > min){
            return repository.findByCompanyAndPriceBetween(company, min, max);
        }
        return new ArrayList<>();
    }

    /**
     *  Search and return a List of cars witch has 4x4 traction in DataBase.
     * @param company Car's manufacturer company.
     * @return A List of cars if it can found registers in DataBase.
     * Return an empty List if it can't found registers.
     */
    public List<Car> findByCompanyAndTraction4x4True(CarCompany company){
        if(company != null){
            return repository.findByCompanyAndTraction4x4True(company);
        }
        return new ArrayList<>();
    }

    /**
     *  Search and return a List of cars witch doesn't have 4x4 traction in DataBase.
     * @param company Car's manufacturer company.
     * @return A List of cars if it can found registers in DataBase.
     * Return an empty List if it can't found registers.
     */
    public List<Car> findByCompanyAndTraction4x4False(CarCompany company){
        if(company != null){
            return repository.findByCompanyAndTraction4x4False(company);
        }
        return new ArrayList<>();
    }

}
