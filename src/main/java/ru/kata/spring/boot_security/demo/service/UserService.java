package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepo;
import javax.transaction.*;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Transactional
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Transactional
    public void updateUser(Long id, User user) {
        user.setId(id);
        userRepo.save(user);
    }

    @Transactional
    public Object getUserById(Long id) {
        Optional<User> foundUser = userRepo.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void removeUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Transactional
    public Object getAllUsers() {
        return userRepo.findAll();
    }

    @Transactional
    public User findByUserName(String username) {
        return userRepo.findByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getAuthorities());
    }
}
