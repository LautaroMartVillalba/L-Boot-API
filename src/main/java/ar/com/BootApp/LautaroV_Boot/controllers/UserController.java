package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.user.UserDTO;
import ar.com.BootApp.LautaroV_Boot.entities.user.UserEntity;
import ar.com.BootApp.LautaroV_Boot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService service;

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<UserEntity>> getById(@PathVariable Long id){
        Optional<UserEntity> result = service.findByUserID(id);
        if (result.isPresent()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/insert")
    public ResponseEntity<UserEntity> getById(@RequestBody UserDTO user){
        if (service.validateUser(user)){
            UserEntity entity = UserEntity.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .isEnabled(user.isEnabled())
                    .accountNoLocked(user.isAccountNoLocked())
                    .credentialsNoExpired(user.isAccountNoExpired())
                    .accountNoExpired(user.isAccountNoExpired()).build();

            service.saveUser(entity);
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.badRequest().build();
    }

}
