package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.Role;
import com.isep.acme.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class UserNeo4jMapper {
    public User schemaToDomain(UserNeo4jSchema schema){
        var roles = new HashSet<Role>();
        for (String role: schema.getAuthorities()) {
            roles.add(new Role(role));
        }
        return new User(schema.getUserId(), schema.getUsername(), schema.getPassword(), schema.getFullName(),
                roles, schema.getNif(), schema.getMorada());
    }

    public UserNeo4jSchema domainToSchema(User user){
        var roles = new HashSet<String>();
        for (Role role: user.getAuthorities()) {
            roles.add(role.getAuthority());
        }
        return new UserNeo4jSchema(user.getUserId(), user.getUsername(), user.getPassword(), user.getFullName(),
                roles, user.getNif(), user.getMorada());
    }
}
