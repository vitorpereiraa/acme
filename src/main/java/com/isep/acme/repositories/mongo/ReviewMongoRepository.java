package com.isep.acme.repositories.mongo;

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
import com.isep.acme.persistence.mongo.ReviewMongoPersistence;
import com.isep.acme.schemas.mongo.ProductMongoMapper;
import com.isep.acme.schemas.mongo.ProductMongoSchema;
import com.isep.acme.schemas.mongo.ReviewMongoMapper;
import com.isep.acme.schemas.mongo.ReviewMongoSchema;
import com.isep.acme.schemas.mongo.UserMongoMapper;
import com.isep.acme.schemas.mongo.UserMongoSchema;
import com.isep.acme.services.iRepositories.ReviewRepository;

@Profile("mongo")
@Component
public class ReviewMongoRepository implements ReviewRepository {

    private static final Logger logger = LoggerFactory.getLogger(ReviewMongoRepository.class);

    @Autowired
    ReviewMongoPersistence reviewPersistence;

    @Autowired
    DatabaseSequenceRepository sequenceGenerator;

    @Autowired
    ReviewMongoMapper reviewMapper;

    @Autowired
    ProductMongoMapper productMapper;

    @Autowired
    UserMongoMapper userMapper;

    @Override
    public Review save(Review review) {
        ReviewMongoSchema schema = reviewMapper.reviewToSchema(review);
        if(review.getIdReview() == null){
            schema.setIdReview(sequenceGenerator.generateSequence(ReviewMongoSchema.SEQUENCE_NAME));
        }
        logger.info("SAVING:" + schema.toString());
        ReviewMongoSchema persisted = reviewPersistence.save(schema);
        return reviewMapper.schemaToReview(persisted);
    }

    @Override
    public void delete(Review review) {
        ReviewMongoSchema schema = reviewMapper.reviewToSchema(review);
        reviewPersistence.delete(schema);
    }

    @Override
    public List<Review> findAll() {
        Iterable<ReviewMongoSchema> reviews = reviewPersistence.findAll();    
        return reviewMapper.schemasToReviews(reviews);
    }

    @Override
    public Optional<Review> findById(Long productId) {
        Optional<ReviewMongoSchema> schema = reviewPersistence.findById(productId);
        logger.info("FOUND by ID:" + schema.toString());
        if(schema.isEmpty()) return Optional.empty();
        Review review = reviewMapper.schemaToReview(schema.get());
        return Optional.of(review);
    }

    @Override
    public List<Review> findByProductId(Product product) {
        ProductMongoSchema productSchema = productMapper.productToSchema(product);
        List<ReviewMongoSchema> reviewSchemas = reviewPersistence.findByProductId(productSchema);
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findPendingReviews() {
        List<ReviewMongoSchema> reviewSchemas = reviewPersistence.findReviewsByStatus("pending");
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findActiveReviews() {
        List<ReviewMongoSchema> reviewSchemas = reviewPersistence.findReviewsByStatus("active");
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findByProductIdStatus(Product product, String status) {
        ProductMongoSchema productSchema = productMapper.productToSchema(product);
        List<ReviewMongoSchema> reviewSchemas = reviewPersistence.findByProductIdStatus(productSchema,status);
        return reviewMapper.schemasToReviews(reviewSchemas);
    }

    @Override
    public List<Review> findByUserId(User user) {
        UserMongoSchema userSchema = userMapper.userToSchema(user);
        List<ReviewMongoSchema> reviewSchemas = reviewPersistence.findByUserId(userSchema);
        return reviewMapper.schemasToReviews(reviewSchemas);
    }
}
