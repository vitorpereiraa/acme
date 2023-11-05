package com.isep.acme.persistence.neo4j;

import com.isep.acme.schemas.neo4j.ImageNeo4jSchema;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ImageNeo4jPersistence extends Neo4jRepository<ImageNeo4jSchema,Long> {
}
