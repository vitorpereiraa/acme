package com.isep.acme.persistence.neo4j;

import com.isep.acme.schemas.neo4j.ProductNeo4jSchema;
import com.isep.acme.schemas.neo4j.ReviewNeo4jSchema;
import com.isep.acme.schemas.neo4j.UserNeo4jSchema;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ReviewNeo4jPersistence extends Neo4jRepository<ReviewNeo4jSchema, Long> {

    @Query("MATCH(r:Review) WHERE r.product=$product RETURN r ORDER BY r.publishingDate DESC")
    List<ReviewNeo4jSchema> findByProductId(ProductNeo4jSchema product);

    @Query("MATCH(r:Review) WHERE r.approvalStatus='pending' RETURN r")
    List<ReviewNeo4jSchema> findPendingReviews();

    @Query("MATCH(r:Review) WHERE r.approvalStatus='active' RETURN r")
    List<ReviewNeo4jSchema> findActiveReviews();

    @Query("MATCH(r:Review) WHERE r.product=$product AND r.approvalStatus=$status RETURN r ORDER BY r.publishingDate DESC")
    List<ReviewNeo4jSchema> findByProductIdStatus(ProductNeo4jSchema product, String status);

    @Query("MATCH(r:Review) WHERE r.user=$user RETURN r ORDER BY r.publishingDate DESC")
    List<ReviewNeo4jSchema> findByUserId(UserNeo4jSchema user);
}
