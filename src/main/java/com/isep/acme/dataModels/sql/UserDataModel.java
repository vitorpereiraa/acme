package com.isep.acme.dataModels.sql;

import com.isep.acme.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@AllArgsConstructor
@Getter
public class UserDataModel {
    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    private String username;

    private String password;

    private String fullName;

    @ElementCollection
    private Set<Role> authorities = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String nif;

    @Column(nullable = false)
    private String morada;

    public UserDataModel() {
    }

    public boolean isEnabled() {
        return true;
    }
}
