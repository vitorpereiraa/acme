package com.isep.acme.schemas.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.User;

@Profile("mongo")
@Component
public class UserMongoMapper {

    public User schemaToUser(UserMongoSchema schema){
        return new User(schema.getUserId(), schema.getUsername(), schema.getPassword(),schema.getFullName(), schema.getAuthorities(),schema.getNif(),schema.getMorada());
    }

    public UserMongoSchema userToSchema(User user){
        return new UserMongoSchema(user.getUserId(),user.getUsername(), user.getPassword(),user.getFullName(),user.getAuthorities(),user.getNif(),user.getMorada());
    }
}
