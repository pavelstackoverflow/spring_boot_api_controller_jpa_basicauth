package com.petprojects.webapi.entity.security;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String mail;
    private boolean isEnabled;
    private boolean isNonBlock;

    @ManyToMany
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name ="user_id"),
            inverseJoinColumns = @JoinColumn(name ="permission_id")
    )
    private Set<ApplicationUserPermission> permissions;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isNonBlock() {
        return isNonBlock;
    }

    public void setNonBlock(boolean nonBlock) {
        isNonBlock = nonBlock;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public ApplicationUser(Long id, String username, String password, String mail, boolean isEnabled, boolean isNonBlock, Set<ApplicationUserPermission> permissions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.isEnabled = isEnabled;
        this.isNonBlock = isNonBlock;
        this.permissions = permissions;
    }

    public ApplicationUser() {
    }

    public ApplicationUser(Long id, String username, String password, String mail, boolean isEnabled, boolean isNonBlock) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.isEnabled = isEnabled;
        this.isNonBlock = isNonBlock;
    }

    public ApplicationUser(String username, String password, String mail) {

        this.username = username;
        this.password = password;
        this.mail = mail;
    }
}
