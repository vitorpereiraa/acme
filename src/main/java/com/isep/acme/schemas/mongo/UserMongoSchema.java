package com.isep.acme.schemas.mongo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.isep.acme.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("mongo")
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMongoSchema {

    @Id
    private Long userId;

    @Indexed(unique = true)
    @Email
    private String username;

    @Field
    private String password;

    @Field
    private String fullName;

    @Field
    private Set<Role> authorities = new HashSet<>();

    @Indexed(unique = true)
    private String nif;

    @Field
    private String morada;
}
