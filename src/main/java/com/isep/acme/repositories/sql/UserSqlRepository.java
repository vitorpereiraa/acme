package com.isep.acme.repositories.sql;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.User;
import com.isep.acme.persistence.sql.UserSqlPersistence;
import com.isep.acme.schemas.sql.UserSqlMapper;
import com.isep.acme.schemas.sql.UserSqlSchema;
import com.isep.acme.services.iRepositories.UserRepository;

@Profile("sql")
@Component
public class UserSqlRepository implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserSqlRepository.class);

    @Autowired
    UserSqlPersistence userRelationalPersistence;

    @Autowired
    UserSqlMapper userMapper;

    @Override
    public User save(User entity) {
        UserSqlSchema schema = userMapper.userToSchema(entity);
        UserSqlSchema persisted = userRelationalPersistence.save(schema);
        return userMapper.schemaToUser(persisted);
    }

    @Override
    public Optional<User> findById(Long userId) {
        Optional<UserSqlSchema> schema = userRelationalPersistence.findById(userId);
        if(schema.isEmpty()) return Optional.empty();
        User user = userMapper.schemaToUser(schema.get());
        return Optional.of(user);
    }

    @Override
    public User getById(Long userId) {
        Optional<UserSqlSchema> schema = userRelationalPersistence.findById(userId);
        if(schema.isEmpty()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        User user = userMapper.schemaToUser(schema.get());
        if (!user.isEnabled()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserSqlSchema> schema = userRelationalPersistence.findByUsername(username);
        if(schema.isEmpty()) return Optional.empty();
        User user = userMapper.schemaToUser(schema.get());
        return Optional.of(user);
    }

    @Override
    public void runConstraints() {
    }

}
