package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {


    private final EntityManager entityManager;

    private final RoleDao roleDao;

    public UserDaoImpl(EntityManager entityManager, RoleDao roleDao) {

        this.entityManager = entityManager;
        this.roleDao = roleDao;
    }

    @Override
    public void saveUser(User user) {
        user.setRoles(List.of(roleDao.findRoleById(2L)));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        entityManager.merge(user);
    }

    @Override
    public Object getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void removeUserById(Long id) {
        entityManager.remove(getUserById(id));

    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT user FROM User user ", User.class).getResultList();
    }

    @Override
    public User findByUserName(String username) {
        return entityManager.createQuery("select user from User user where user.username=:username", User.class)
                .setParameter("username", username).getSingleResult();
    }
}