package com.taskman.model.entities;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    private String email;
    private String password;
    private String fullName;
    private boolean isActive;
    private String userImg;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRoleEntity> userRoles;

    @Column(name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "full_name", nullable = false)
    public String getFirstName() {
        return fullName;
    }
    public void setFirstName(String firstName) {
        this.fullName = fullName;
    }

    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }

    public String getUserImg() {
        return userImg;
    }
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @ManyToMany
    public List<UserRoleEntity> getUserRoles() {
        return userRoles;
    }
    public void setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
