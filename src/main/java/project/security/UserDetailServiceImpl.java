package project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import project.entity.User;
import project.exception.NotFoundException;
import project.service.UserService;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService service;

    @Override
    public UserDetails loadUserByUsername(String username){
        User login = service.getByLogin(username);
        if (login == null) {
            throw new NotFoundException("User with login " + username + " not found");
        }
        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                login.getLogin(),
                login.getPassword(),
                List.of(new SimpleGrantedAuthority(login.getRole().name()))
        );
        return user;
    }
}
