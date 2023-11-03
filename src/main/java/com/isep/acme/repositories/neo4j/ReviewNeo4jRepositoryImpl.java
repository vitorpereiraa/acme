package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.persistence.neo4j.ReviewNeo4jPersistence;
import com.isep.acme.schemas.neo4j.ProductNeo4jMapper;
import com.isep.acme.schemas.neo4j.ReviewNeo4jMapper;
import com.isep.acme.schemas.neo4j.UserNeo4jMapper;
import com.isep.acme.services.iRepositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("neo4j")
public class ReviewNeo4jRepositoryImpl implements ReviewRepository {

    @Autowired
    private ReviewNeo4jPersistence persistence;

    @Autowired
    private ReviewNeo4jMapper reviewMapper;

    @Autowired
    private ProductNeo4jMapper productMapper;

    @Autowired
    private UserNeo4jMapper userMapper;

    @Override
    public Review save(Review review) {
        var schema = reviewMapper.domainToSchema(review);
        var schemaSaved = persistence.save(schema);
        return reviewMapper.schemaToDomain(schemaSaved);
    }

    @Override
    public void delete(Review review) {
        var schema = reviewMapper.domainToSchema(review);
        persistence.delete(schema);
    }

    @Override
    public List<Review> findAll() {
        var schemas = persistence.findAll();
        return reviewMapper.schemaListToDomainList(schemas);
    }

    @Override
    public Optional<Review> findById(Long productId) {
        var schema = persistence.findById(productId);
        return schema.map(reviewMapper::schemaToDomain);
    }

    @Override
    public List<Review> findByProductId(Product product) {
        var productSchema = productMapper.domainToSchema(product);
        var schemas = persistence.findByProductId(productSchema.getProductID());
        return reviewMapper.schemaListToDomainList(schemas);
    }

    @Override
    public List<Review> findPendingReviews() {
        var schemas = persistence.findPendingReviews();
        return reviewMapper.schemaListToDomainList(schemas);
    }

    @Override
    public List<Review> findActiveReviews() {
        var schemas = persistence.findActiveReviews();
        return reviewMapper.schemaListToDomainList(schemas);
    }

    @Override
    public List<Review> findByProductIdStatus(Product product, String status) {
        var productSchema = productMapper.domainToSchema(product);
        var schemas = persistence.findByProductIdStatus(productSchema.getProductID(), status);
        return reviewMapper.schemaListToDomainList(schemas);
    }

    @Override
    public List<Review> findByUserId(User user) {
        var userSchema = userMapper.domainToSchema(user);
        var schemas = persistence.findByUserId(userSchema.getUserId());
        return reviewMapper.schemaListToDomainList(schemas);
    }
}
