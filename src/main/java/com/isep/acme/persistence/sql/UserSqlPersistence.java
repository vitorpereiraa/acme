package com.isep.acme.persistence.sql;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.sql.UserSqlSchema;

import java.util.Optional;

@Profile("sql")
@Repository
@CacheConfig(cacheNames = "users")
public interface UserSqlPersistence extends CrudRepository<UserSqlSchema, Long> {

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#p0.userId", condition = "#p0.userId != null"),
            @CacheEvict(key = "#p0.username", condition = "#p0.username != null") })
    <S extends UserSqlSchema> S save(S entity);

    @Override
    @Cacheable
    Optional<UserSqlSchema> findById(Long userId);

    @Cacheable
    Optional<UserSqlSchema> findByUsername(String username);
}