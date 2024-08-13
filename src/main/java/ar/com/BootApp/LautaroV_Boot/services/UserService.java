package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.tool.Tool;
import ar.com.BootApp.LautaroV_Boot.entities.user.User;
import ar.com.BootApp.LautaroV_Boot.entities.user.UserRole;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.DuplicatedToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.NullToolException;
import ar.com.BootApp.LautaroV_Boot.exceptions.tool.types.ToolEmptyDataBaseException;
import ar.com.BootApp.LautaroV_Boot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private UserRepository repository;

    boolean validateUser(User user){
        if (!Objects.equals(user.getEmail(), "") || !Objects.equals(user.getName(), "") || !Objects.equals(user.getRole(), null)){
            return true;
        }
        return false;
    }

    /*----------------Default Methods----------------*/

    /**
     * Search and return a List of all users in DataBase.
     *
     * @return List af all users in DataBase List if it has some registers.
     */
    public List<User> findAllUsers(){
        List<User> result = repository.findAll();
        if (result.isEmpty()) {
            throw new RuntimeException("Empty Database.");
        }
        return result;
    }

    /**
     * Search a user in DataBase by his ID number (long type).
     *
     * @param id User's identification.
     * @return Optional of tool if it can find a register. Optional.Empty if it can't.
     */
    public Optional<User> findByUserID(Long id) {
        return repository.findById(id);
    }

    /**
     * Save a user in DataBase, using a first validation by all tool's parameters.
     *
     * @param user User Object to persist in DataBase.
     */
    public boolean saveUser(User user){
        if (!validateUser(user)) {
            throw new RuntimeException("Not valid user.");
        }
        Optional<User> userRepo = repository.findByEmail(user.getEmail());
        if (userRepo.isPresent()) {
            throw new RuntimeException("User already exist.");
        }
        repository.save(user);
        return true;
    }

    /**
     * Search one user in DataBase witch matches with one existing. If it can find, delete the found car.
     *
     * @param id User's identification number (long type).
     * @return False if it can't find a tool's id witch matches in DataBase. True if it can find and delete.
     */
    public boolean deleteUserByID(Long id) {
        Optional<User> userFind = repository.findById(id);
        if (userFind.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /*--------------------Custom Methods---------------------*/

    public Optional<User> findUserByEmail(String email){
        if (!Objects.equals(email, "") && email.contains("@gmail.com") || email.contains("@hotmail.com")){
            return repository.findByEmail(email);
        }
        return Optional.empty();
    }

    public List<User> findUserByRole(UserRole role){
        if (role != null){
            return repository.findByRole(role);
        }
        return new ArrayList<>();
    }

    public List<User> findUserByNameAndRole(String name, UserRole role){
        if (Objects.equals(name, "") && role != null){
            return repository.findByNameAndRole(name,role);
        }
        return new ArrayList<>();
    }

}
