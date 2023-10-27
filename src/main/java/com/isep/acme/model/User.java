package com.isep.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    private Long userId;

    @Email
    private String username;

    private String password;

    private String fullName;

    private Set<Role> authorities = new HashSet<>();

    private String nif;

    private String morada;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    protected User() {}

    public User(final String username, final String password){
        this.username = username;
        this.password = password;
    }


    public User(final String username, final String password, final String fullName, final String nif, final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void addAuthority(Role r) {
        authorities.add(r);
    }

    public void setNif(String nif) {
        if(nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

