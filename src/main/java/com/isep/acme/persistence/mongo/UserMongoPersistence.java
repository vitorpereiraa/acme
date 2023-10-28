package com.isep.acme.persistence.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.mongo.UserMongoSchema;

@Profile("mongo")
@Repository
public interface UserMongoPersistence extends MongoRepository<UserMongoSchema, Long>{
    
}
