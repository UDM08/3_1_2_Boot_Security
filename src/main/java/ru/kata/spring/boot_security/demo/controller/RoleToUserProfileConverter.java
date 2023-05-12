
package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.service.RoleService;

@Component
public class RoleToUserProfileConverter implements Converter<String, Role> {
    RoleService roleService;

    @Autowired
    public RoleToUserProfileConverter(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role convert(String id) {

        return roleService.findRoleById(Long.valueOf(id));

    }
}

