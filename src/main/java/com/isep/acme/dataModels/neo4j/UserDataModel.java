package com.isep.acme.dataModels.neo4j;

import com.isep.acme.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


import java.util.HashSet;
import java.util.Set;

@Node("User")
@Getter
@AllArgsConstructor
public class UserDataModel {

    @Id
    @GeneratedValue
    private Long userId;

    // TODO make unique
    //@Email
    private String username;

    private String password;

    private String fullName;

    @Relationship(direction = Relationship.Direction.OUTGOING, type = "is")
    private Set<Role> authorities = new HashSet<>();

    // TODO make unique
    private String nif;

    private String morada;
}
