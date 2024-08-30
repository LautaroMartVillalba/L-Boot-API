package ar.com.BootApp.LautaroV_Boot.services;

import ar.com.BootApp.LautaroV_Boot.entities.user.UserEntity;
import ar.com.BootApp.LautaroV_Boot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findUserEntityByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User with email = '" + email + "' doesn't exists."));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userEntity.getRole().forEach(role -> authorities.
                add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleName().name()))));

        userEntity.getRole()
                .stream()
                .flatMap(role -> role.getPrivileges().stream())
                .forEach(privileges -> authorities
                                        .add(new SimpleGrantedAuthority(privileges.getName().name())));

        return new User(userEntity.getEmail()
                        ,userEntity.getPassword()
                        ,userEntity.isEnabled()
                        ,userEntity.isAccountNoExpired()
                        ,userEntity.isCredentialsNoExpired()
                        ,userEntity.isAccountNoLocked()
                        ,authorities);
    }
}
