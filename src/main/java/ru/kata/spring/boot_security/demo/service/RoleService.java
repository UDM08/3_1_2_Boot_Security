package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.repositories.RoleRepo;

public class RoleService  {

    private final RoleRepo roleRepo;

    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

}


