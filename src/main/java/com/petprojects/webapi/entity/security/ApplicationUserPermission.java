package com.petprojects.webapi.entity.security;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ApplicationUserPermission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String permission;

    @ManyToMany(mappedBy = "permissions")
    private Set<ApplicationUser> users;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Set<ApplicationUser> getUsers() {
        return users;
    }

    public void setUsers(Set<ApplicationUser> users) {
        this.users = users;
    }

    public ApplicationUserPermission(Long id, String permission, Set<ApplicationUser> users) {
        this.id = id;
        this.permission = permission;
        this.users = users;
    }

    public ApplicationUserPermission() {
    }

    public ApplicationUserPermission(Long id, String permission) {
        this.id = id;
        this.permission = permission;
    }

    public ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
}
