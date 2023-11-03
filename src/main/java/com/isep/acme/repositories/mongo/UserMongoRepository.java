package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.User;
import com.isep.acme.persistence.mongo.UserMongoPersistence;
import com.isep.acme.schemas.mongo.UserMongoMapper;
import com.isep.acme.schemas.mongo.UserMongoSchema;
import com.isep.acme.services.iRepositories.UserRepository;

@Profile("mongo")
@Component
public class UserMongoRepository implements UserRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserMongoRepository.class);

    @Autowired
    UserMongoPersistence userRelationalPersistence;

    @Autowired
    DatabaseSequenceRepository sequenceGenerator;

    @Autowired
    UserMongoMapper userMapper;

    @Override
    public User save(User entity) {
        UserMongoSchema schema = userMapper.userToSchema(entity);
        if(entity.getUserId() == null){
            schema.setUserId(sequenceGenerator.generateSequence(UserMongoSchema.SEQUENCE_NAME));
        }
        UserMongoSchema persisted = userRelationalPersistence.save(schema);
        return userMapper.schemaToUser(persisted);
    }

    @Override
    public Optional<User> findById(Long userId) {
        Optional<UserMongoSchema> schema = userRelationalPersistence.findByUserId(userId);
        if(schema.isEmpty()) return Optional.empty();
        User user = userMapper.schemaToUser(schema.get());
        return Optional.of(user);
    }

    @Override
    public User getById(Long userId) {
        Optional<UserMongoSchema> schema = userRelationalPersistence.findByUserId(userId);
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
        Optional<UserMongoSchema> schema = userRelationalPersistence.findByUsername(username);
        if(schema.isEmpty()) return Optional.empty();
        User user = userMapper.schemaToUser(schema.get());
        return Optional.of(user);
    }

    @Override
    public void runConstraints() {

    }

}
