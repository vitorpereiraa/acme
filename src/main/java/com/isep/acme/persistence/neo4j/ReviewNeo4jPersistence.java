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
    @Query(Neo4jPersistenceConstants.Review.Match +
            "WHERE ID(p)=$productId " +
            Neo4jPersistenceConstants.Review.MatchVotes +
            Neo4jPersistenceConstants.Review.Return +
            "ORDER BY r.publishingDate DESC")
    List<ReviewNeo4jSchema> findByProductId(Long productId);

    @Query(Neo4jPersistenceConstants.Review.Match +
            "WHERE r.approvalStatus='pending' " +
            Neo4jPersistenceConstants.Review.MatchVotes +
            Neo4jPersistenceConstants.Review.Return)
    List<ReviewNeo4jSchema> findPendingReviews();

    @Query(Neo4jPersistenceConstants.Review.Match +
            "WHERE r.approvalStatus='active' " +
            Neo4jPersistenceConstants.Review.MatchVotes +
            Neo4jPersistenceConstants.Review.Return)
    List<ReviewNeo4jSchema> findActiveReviews();

    @Query(Neo4jPersistenceConstants.Review.Match +
            "WHERE id(p)=$productID AND r.approvalStatus=$status " +
            Neo4jPersistenceConstants.Review.MatchVotes +
            Neo4jPersistenceConstants.Review.Return +
            "ORDER BY r.publishingDate DESC")
    List<ReviewNeo4jSchema> findByProductIdStatus(Long productID, String status);

    @Query(Neo4jPersistenceConstants.Review.Match +
            "WHERE ID(u)=$userId " +
            Neo4jPersistenceConstants.Review.MatchVotes +
            Neo4jPersistenceConstants.Review.Return +
            "ORDER BY r.publishingDate DESC")
    List<ReviewNeo4jSchema> findByUserId(Long userId);
}
