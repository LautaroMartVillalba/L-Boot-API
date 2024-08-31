package ar.com.BootApp.LautaroV_Boot.entities.user.user;

import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO extends UserEntity {

    private String name;
    private String email;
    private String password;
    private Set<RoleEntity> role = new HashSet<>();
    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialsNoExpired;

}
