package com.isep.acme.schemas.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Set;

@Node("User")
@Getter
@AllArgsConstructor
public class UserNeo4jSchema {

    @Id
    @GeneratedValue
    private Long userId;

    // TODO make unique
    //@Email
    private String username;

    private String password;

    private String fullName;

    //@Relationship(direction = Relationship.Direction.OUTGOING, type = "is")
    private Set<String> authorities;

    // TODO make unique
    private String nif;

    private String morada;
}
