package com.isep.acme.repositories.sql;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.persistence.sql.ReviewSqlPersistence;
import com.isep.acme.schemas.sql.ProductSqlMapper;
import com.isep.acme.schemas.sql.ProductSqlSchema;
import com.isep.acme.schemas.sql.ReviewSqlMapper;
import com.isep.acme.schemas.sql.ReviewSqlSchema;
import com.isep.acme.schemas.sql.UserSqlMapper;
import com.isep.acme.schemas.sql.UserSqlSchema;
import com.isep.acme.services.iRepositories.ReviewRepository;

@Profile("sql")
@Component
public class ReviewSqlRepository implements ReviewRepository {

    private static final Logger logger = LoggerFactory.getLogger(ReviewSqlRepository.class);

    @Autowired
    ReviewSqlPersistence reviewPersistence;

    @Autowired
    ReviewSqlMapper reviewMapper;

    @Autowired
    ProductSqlMapper productMapper;

    @Autowired
    UserSqlMapper userMapper;

    @Override
    public Review save(Review review) {
        ReviewSqlSchema schema = reviewMapper.reviewToSchema(review);
        logger.info("SAVING:" + schema.toString());
        ReviewSqlSchema persisted = reviewPersistence.save(schema);
        return reviewMapper.schemaToReview(persisted);
    }

    @Override
    public void delete(Review review) {
        ReviewSqlSchema schema = reviewMapper.reviewToSchema(review);
        reviewPersistence.delete(schema);
    }

    @Override
    public List<Review> findAll() {
        Iterable<ReviewSqlSchema> reviews = reviewPersistence.findAll();    
        return reviewMapper.schemasToReviews(reviews);
    }

    @Override
    public Optional<Review> findById(Long productId) {
        Optional<ReviewSqlSchema> schema = reviewPersistence.findById(productId);
        logger.info("FOUND by ID:" + schema.toString());
        if(schema.isEmpty()) return Optional.empty();
        Review review = reviewMapper.schemaToReview(schema.get());
        return Optional.of(review);
    }

    @Override
    public List<Review> findByProductId(Product product) {
        ProductSqlSchema productSchema = productMapper.productToSchema(product);
        List<ReviewSqlSchema> reviewSchemas = reviewPersistence.findByProductId(productSchema);
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findPendingReviews() {
        List<ReviewSqlSchema> reviewSchemas = reviewPersistence.findPendingReviews();
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findActiveReviews() {
        List<ReviewSqlSchema> reviewSchemas = reviewPersistence.findActiveReviews();
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findByProductIdStatus(Product product, String status) {
        ProductSqlSchema productSchema = productMapper.productToSchema(product);
        List<ReviewSqlSchema> reviewSchemas = reviewPersistence.findByProductIdStatus(productSchema,status);
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findByUserId(User user) {
        UserSqlSchema userSchema = userMapper.userToSchema(user);
        List<ReviewSqlSchema> reviewSchemas = reviewPersistence.findByUserId(userSchema);
        return reviewMapper.schemasToReviews(reviewSchemas);
    }
}
