package ar.com.BootApp.LautaroV_Boot.entities.user;

import ar.com.BootApp.LautaroV_Boot.entities.user.role.RoleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "db-user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "RS_users-roles",
            joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name = "roleID"))
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Set<RoleEntity> role; //Employing a Set interface ensures that there are no repeated roles.

    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialsNoExpired;

}
