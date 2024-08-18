package ar.com.BootApp.LautaroV_Boot.entities.user.privileges;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class PrivilegesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "privilege_name")
    @Enumerated(EnumType.STRING)
    private PrivilegesEnum name;

}
