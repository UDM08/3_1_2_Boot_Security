package ru.kata.spring.boot_security.demo.dao;


import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {


    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(Long id, User user) {
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