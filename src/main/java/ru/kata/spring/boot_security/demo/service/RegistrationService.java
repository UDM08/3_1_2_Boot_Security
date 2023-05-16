package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;


@Service
public class RegistrationService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private  final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserDao userDao, RoleDao roleDao, @Lazy PasswordEncoder passwordEncoder ) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void register(User user) {
        user.setRoles(List.of(roleDao.findRoleById(2L)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }
}
