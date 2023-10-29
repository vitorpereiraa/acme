package com.isep.acme.persistence.neo4j;

import com.isep.acme.schemas.neo4j.UserNeo4jSchema;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserNeo4jPersistence extends Neo4jRepository<UserNeo4jSchema, Long> {

    //TODO check cacheable
    @Query("MATCH(u:User) WHERE u.username = $username RETURN u")
    Optional<UserNeo4jSchema> findByUsername(String username);
}
