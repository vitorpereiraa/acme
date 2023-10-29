package com.isep.acme.persistence.neo4j;

import com.isep.acme.schemas.neo4j.AggregatedRatingNeo4jjSchema;
import com.isep.acme.schemas.neo4j.ProductNeo4jSchema;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface AggregatedRatingNeo4jPersistence extends Neo4jRepository<AggregatedRatingNeo4jjSchema, Long> {
    @Query("MATCH(ar:AggregatedRating) WHERE ar.product = :product RETURN ar")
    Optional<AggregatedRatingNeo4jjSchema> findByProductId(ProductNeo4jSchema product);
}
