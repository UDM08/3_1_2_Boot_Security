package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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


    @Autowired
    public RegistrationService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;

        this.roleDao = roleDao;
    }

    @Transactional
    public void register(User user) {
        user.setRoles(List.of(roleDao.findRoleById(2L)));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDao.saveUser(user);
    }
}
