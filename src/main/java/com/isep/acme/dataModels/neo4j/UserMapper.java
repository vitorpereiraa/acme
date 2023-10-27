package com.isep.acme.dataModels.neo4j;

import com.isep.acme.model.User;

public class UserMapper {
    public static User SchemaToDomain(UserDataModel schema){
        return new User(schema.getUserId(), schema.getUsername(), schema.getPassword(), schema.getFullName(),
                schema.getAuthorities(), schema.getNif(), schema.getMorada());
    }

    public static UserDataModel DomainToSchema(User user){
        return new UserDataModel(user.getUserId(), user.getUsername(), user.getPassword(), user.getFullName(),
                user.getAuthorities(), user.getNif(), user.getMorada());
    }
}
