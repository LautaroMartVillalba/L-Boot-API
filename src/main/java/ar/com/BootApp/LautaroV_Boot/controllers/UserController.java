package ar.com.BootApp.LautaroV_Boot.controllers;

import ar.com.BootApp.LautaroV_Boot.entities.user.UserDTO;
import ar.com.BootApp.LautaroV_Boot.entities.user.UserEntity;
import ar.com.BootApp.LautaroV_Boot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_DEVELOPER')")
public class UserController {

    private UserService service;

    @GetMapping("/by-id/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Optional<UserEntity>> getById(@PathVariable Long id){
        Optional<UserEntity> result = service.findByUserID(id);
        if (result.isPresent()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<UserEntity>> getAllUsers(){
        List<UserEntity> result = service.findAllUsers();

        if(result.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/insert")
    @PreAuthorize("permitAll()")
    public ResponseEntity<UserDTO> insertUser(@RequestBody UserDTO user){
        if (service.validateUser(user)){
            service.saveUser(user);
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

}
