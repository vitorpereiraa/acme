package com.isep.acme.schemas.sql;

import com.isep.acme.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

import org.springframework.context.annotation.Profile;

import java.util.*;

@Profile("sql")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="\"User\"")
public class UserSqlSchema {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    @Email
    private String username;

    private String password;

    private String fullName;

    @ElementCollection
    private Set<Role> authorities = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String nif;

    @Column(nullable = false)
    private String morada;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    // public UserRelationalSchema(final String username, final String password){
    //     this.username = username;
    //     this.password = password;
    // }

    // public UserRelationalSchema(final String username, final String password, final String fullName, final String nif, final String morada) {
    //     this.username = username;
    //     this.password = password;
    //     this.fullName = fullName;
    //     this.nif = nif;
    //     this.morada = morada;
    // }
}

