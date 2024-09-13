package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.user.user.UserDTO;
import ar.com.BootApp.LautaroV_Boot.entities.user.user.UserEntity;
import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEnum;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.ExistingObjectException;
import ar.com.BootApp.LautaroV_Boot.exceptions.type.NullObjectException;
import ar.com.BootApp.LautaroV_Boot.repositories.UserRepository;
import lombok.AllArgsConstructor;
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

    /**
     * Verifies the inserted User data.
     * @param user User information received from POST method.
     * @return False if one or more of the data is blank, empty or null. True if not.
     */
    public boolean validateUser(UserEntity user){
        if (!Objects.equals(user.getEmail(), "") || !Objects.equals(user.getName(), "") || !Objects.equals(user.getRole(), null)){
            return true;
        }
        return false;
    }
    /**
     * Verifies the inserted User data.
     * @param user User information received from POST method.
     * @return False if one or more of the data is blank, empty or null. True if not.
     */
    public boolean validateUser(UserDTO user){
        if (!Objects.equals(user.getEmail(), "") || !Objects.equals(user.getName(), "") || !Objects.equals(user.getRole(), null)){
            return true;
        }
        return false;
    }

    /*----------------Default Methods----------------*/

    /**
     * Searches and returns a list of all users in the database.
     *
     * @return A list of all users in the database if there are any records.
     */
    public List<UserEntity> findAllUsers(){
        List<UserEntity> result = repository.findAll();
        if (result.isEmpty()) {
            throw new RuntimeException("Empty Database.");
        }
        return result;
    }

    /**
     * Searches for a user in the database by their ID number (of type long).
     *
     * @param id The user's identification number.
     * @return An Optional of UserEntity if a record is found. Optional.empty() if not.
     */
    public Optional<UserEntity> findByUserID(Long id) {
        return repository.findById(id);
    }

    /**
     * Saves a user in the database after validating all the user's parameters.
     *
     * @param user The User object to persist in the database.
     */
    public boolean saveUser(UserEntity user) throws ExistingObjectException, NullObjectException {
        if (!validateUser(user)) {
            throw new NullObjectException();
        }
        Optional<UserEntity> userRepo = repository.findUserEntityByEmail(user.getEmail());
        if (userRepo.isPresent()) {
            throw new ExistingObjectException();
        }
        UserEntity userSave = UserEntity.builder()
                .name(user.getName())
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

    /**
     * Saves a user in the database after validating all the user's parameters.
     *
     * @param user The UserDTO object to persist in the database.
     */
    public boolean saveUser(UserDTO user) throws ExistingObjectException, NullObjectException {
        if (!validateUser(user)) {
            throw new NullObjectException();
        }
        Optional<UserEntity> userRepo = repository.findUserEntityByEmail(user.getEmail());
        if (userRepo.isPresent()) {
            throw new ExistingObjectException();
        }

        UserEntity userSave = UserEntity.builder()
                .name(user.getName())
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

    /**
     * Searches for a user in the database that matches an existing one. If found, deletes the corresponding user.
     *
     * @param id The user's identification number (of type long).
     * @return False if no matching user ID is found in the database. True if a matching user is found and deleted.
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

    /**
     * Searches for a unique user by their email.
     * @param email The user's email.
     * @return An Optional of UserEntity if found. Optional.empty() if no match is found in the database.
     */
    public Optional<UserEntity> findUserByEmail(String email){
        if (!Objects.equals(email, "") && email.contains("@gmail.com") || email.contains("@hotmail.com")){
            return repository.findUserEntityByEmail(email);
        }
        return Optional.empty();
    }

    /**
     * Searches for users in the database by their role.
     * @param role The user's role.
     * @return A list of UserEntity objects.
     */
    public List<UserEntity> findUserByRole(RoleEnum role){
        if (role != null){
            return repository.findByRole(role);
        }
        return new ArrayList<>();
    }

    /**
     * Searches for users in the database by their role and name.
     * @param name The user's name.
     * @param role The user's role.
     * @return A list of UserEntity objects.
     */
    public List<UserEntity> findUserByNameAndRole(String name, RoleEnum role){
        if (Objects.equals(name, "") && role != null){
            return repository.findByNameAndRole(name,role);
        }
        return new ArrayList<>();
    }

}
