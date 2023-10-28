package com.isep.acme.persistence.mongo;

import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.mongo.UserMongoSchema;

@Profile("mongo")
@Repository
public interface UserMongoPersistence extends MongoRepository<UserMongoSchema, Long>{

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#p0.userId", condition = "#p0.userId != null"),
            @CacheEvict(key = "#p0.username", condition = "#p0.username != null") })
    <S extends UserMongoSchema> S save(S entity);

    @Cacheable
    Optional<UserMongoSchema> findByUserId(Long usedId);

    @Cacheable
    Optional<UserMongoSchema> findByUsername(String username);
}
