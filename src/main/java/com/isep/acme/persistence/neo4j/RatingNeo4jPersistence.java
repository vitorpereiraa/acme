package com.isep.acme.persistence.neo4j;

import com.isep.acme.schemas.neo4j.RatingNeo4jSchema;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface RatingNeo4jPersistence extends Neo4jRepository<RatingNeo4jSchema, Long> {

    @Query("MATCH(r:Rating) WHERE r.rate = :rate RETURN r")
    Optional<RatingNeo4jSchema> findByRate(Double rate);
}
