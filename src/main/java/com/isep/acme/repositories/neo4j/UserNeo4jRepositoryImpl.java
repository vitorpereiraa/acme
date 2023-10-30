package com.isep.acme.repositories.neo4j;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.User;
import com.isep.acme.persistence.neo4j.UserNeo4jPersistence;
import com.isep.acme.schemas.neo4j.UserNeo4jMapper;
import com.isep.acme.services.iRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserNeo4jRepositoryImpl implements UserRepository {

    @Autowired
    private UserNeo4jPersistence persistence;

    @Autowired
    private UserNeo4jMapper userMapper;


    @Override
    public User save(User entity) {
        var userSchema = userMapper.domainToSchema(entity);
        var userSchemaSaved = persistence.save(userSchema);
        return userMapper.schemaToDomain(userSchemaSaved);
    }

    @Override
    public Optional<User> findById(Long userId) {
        var userSchema = persistence.findById(userId);
        return userSchema.map(userMapper::schemaToDomain);
    }

    @Override
    public User getById(Long userId) {
        var userSchema = persistence.findById(userId);
        if (userSchema.isEmpty()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        var user = userMapper.schemaToDomain(userSchema.get());
        if (!user.isEnabled()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var userSchema = persistence.findByUsername(username);
        return userSchema.map(userMapper::schemaToDomain);
    }

    @Override
    public void runConstraints() {
        persistence.runUsernameConstraint();
        persistence.runNifConstraint();
    }
}
