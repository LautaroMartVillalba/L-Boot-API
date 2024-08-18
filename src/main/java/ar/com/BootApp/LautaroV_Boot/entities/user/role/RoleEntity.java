package ar.com.BootApp.LautaroV_Boot.entities.user.role;

import ar.com.BootApp.LautaroV_Boot.entities.user.privileges.PrivilegesEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "db_roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role_name")
    private RoleEnum roleName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "RS_roles_privileges",
            joinColumns = @JoinColumn(name ="roleID"),
            inverseJoinColumns = @JoinColumn(name = "privilegeID"))
    private Set<PrivilegesEntity> privileges;

}
