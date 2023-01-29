package ru.kata.spring.boot_security.demo.model;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "authority")
    private String authority;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public Role() {
    }

    public Role(Long roleId, String authority) {
        this.roleId = roleId;
        this.authority = authority;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", authority='" + authority + '\'' +
                ", users=" + users +
                '}';
    }
}
