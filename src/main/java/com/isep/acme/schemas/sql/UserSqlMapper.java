package com.isep.acme.schemas.sql;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.User;

@Profile("sql")
@Component
public class UserSqlMapper {
    
    public User schemaToUser(UserSqlSchema schema){
        return new User(schema.getUserId(), schema.getUsername(), schema.getPassword(),schema.getFullName(), schema.getAuthorities(),schema.getNif(),schema.getMorada());
    }

    public UserSqlSchema userToSchema(User user){
        return new UserSqlSchema(user.getUserId(),user.getUsername(), user.getPassword(),user.getFullName(),user.getAuthorities(),user.getNif(),user.getMorada());
    }
}
