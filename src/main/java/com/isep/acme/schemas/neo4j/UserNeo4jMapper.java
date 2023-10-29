package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserNeo4jMapper {
    public User schemaToDomain(UserNeo4jSchema schema){
        return new User(schema.getUserId(), schema.getUsername(), schema.getPassword(), schema.getFullName(),
                schema.getAuthorities(), schema.getNif(), schema.getMorada());
    }

    public UserNeo4jSchema domainToSchema(User user){
        return new UserNeo4jSchema(user.getUserId(), user.getUsername(), user.getPassword(), user.getFullName(),
                user.getAuthorities(), user.getNif(), user.getMorada());
    }
}
