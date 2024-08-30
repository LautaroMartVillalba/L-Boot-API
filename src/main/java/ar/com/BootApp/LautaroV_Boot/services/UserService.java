package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.user.UserDTO;
import ar.com.BootApp.LautaroV_Boot.entities.user.UserEntity;
import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEntity;
import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEnum;
import ar.com.BootApp.LautaroV_Boot.repositories.RoleEntityRepository;
import ar.com.BootApp.LautaroV_Boot.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@AllArgsConstructor
@Service
public class UserService {

    @Autowired
    private PasswordEncoder encoder;

    private UserRepository repository;
    private RoleEntityRepository roleRepo;

    public boolean validateUser(UserEntity user){
        if (!Objects.equals(user.getEmail(), "") || !Objects.equals(user.getUserName(), "") || !Objects.equals(user.getRole(), null)){
            return true;
        }
        return false;
    }
    public boolean validateUser(UserDTO user){
        if (!Objects.equals(user.getEmail(), "") || !Objects.equals(user.getUserName(), "") || !Objects.equals(user.getRole(), null)){
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
    public List<UserEntity> findAllUsers(){
        List<UserEntity> result = repository.findAll();
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
    public Optional<UserEntity> findByUserID(Long id) {
        return repository.findById(id);
    }

    /**
     * Save a user in DataBase, using a first validation by all tool's parameters.
     *
     * @param user User Object to persist in DataBase.
     */
    public boolean saveUser(UserEntity user){
        if (!validateUser(user)) {
            throw new RuntimeException("Not valid user.");
        }
        Optional<UserEntity> userRepo = repository.findUserEntityByEmail(user.getEmail());
        if (userRepo.isPresent()) {
            throw new RuntimeException("User already exist.");
        }

        UserEntity userSave = UserEntity.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .credentialsNoExpired(true)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .role(user.getRole())
                .build();

        repository.save(userSave);
        return true;
    }

    public boolean saveUser(UserDTO user){
        if (!validateUser(user)) {
            throw new RuntimeException("Not valid user.");
        }
        Optional<UserEntity> userRepo = repository.findUserEntityByEmail(user.getEmail());
        if (userRepo.isPresent()) {
            throw new RuntimeException("User already exist.");
        }

        Optional<RoleEntity> userRole = roleRepo.findByRoleName(String.valueOf(user.getRole()));
        RoleEntity role = userRole.get();

        UserEntity userSave = UserEntity.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .password(encoder.encode(user.getPassword()))
                .credentialsNoExpired(true)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .role(Set.of(role))
                .build();

        repository.save(userSave);
        return true;
    }

    /**
     * Search one user in DataBase witch matches with one existing. If it can find, delete the found car.
     *
     * @param id User's identification number (long type).
     * @return False if it can't find a tool's id witch matches in DataBase. True if it can find and delete.
     */
    public boolean deleteUserByID(Long id) {
        Optional<UserEntity> userFind = repository.findById(id);
        if (userFind.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    /*--------------------Custom Methods---------------------*/

    public Optional<UserEntity> findUserByEmail(String email){
        if (!Objects.equals(email, "") && email.contains("@gmail.com") || email.contains("@hotmail.com")){
            return repository.findUserEntityByEmail(email);
        }
        return Optional.empty();
    }

    public List<UserEntity> findUserByRole(RoleEnum role){
        if (role != null){
            return repository.findByRole(role);
        }
        return new ArrayList<>();
    }

    public List<UserEntity> findUserByNameAndRole(String name, RoleEnum role){
        if (Objects.equals(name, "") && role != null){
            return repository.findByUserNameAndRole(name,role);
        }
        return new ArrayList<>();
    }

}
