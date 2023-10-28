package com.isep.acme.schemas.mongo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.model.Rating;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.model.Vote;

@Profile("mongo")
@Component
public class ReviewMongoMapper {
    
    @Autowired
    ProductMongoMapper productMapper;

    @Autowired
    RatingMongoMapper ratingMapper;

    @Autowired
    UserMongoMapper userMapper;

    @Autowired
    VoteMongoMapper votesMapper;

    public Review schemaToReview(ReviewMongoSchema schema){
        Product product = productMapper.schemaToProduct(schema.getProduct());
        Rating rating = ratingMapper.schemaToRating(schema.getRating());
        User user = userMapper.schemaToUser(schema.getUser());
        List<Vote> upVotes = votesMapper.schemasToVotes(schema.getUpVote());
        List<Vote> downVotes = votesMapper.schemasToVotes(schema.getDownVote());
        return new Review(
            schema.getIdReview(),
            schema.getVersion(),
            schema.getApprovalStatus(),
            schema.getReviewText(),
            upVotes,
            downVotes,
            schema.getReport(),
            schema.getPublishingDate(),
            schema.getFunFact(),
            product,
            rating,
            user
        );
    }

    public ReviewMongoSchema reviewToSchema (Review review){
        ProductMongoSchema product = productMapper.productToSchema(review.getProduct());
        RatingMongoSchema rating = ratingMapper.ratingToSchema(review.getRating());
        UserMongoSchema user = userMapper.userToSchema(review.getUser());
        List<VoteMongoSchema> upVotes = votesMapper.votesToSchemas(review.getUpVote());
        List<VoteMongoSchema> downVotes = votesMapper.votesToSchemas(review.getDownVote());
        return new ReviewMongoSchema(
            review.getIdReview(),
            review.getVersion(),
            review.getApprovalStatus(),
            review.getReviewText(),
            upVotes,
            downVotes,
            review.getReport(),
            review.getPublishingDate(),
            review.getFunFact(),
            product,
            user,
            rating
        );
    }

    public List<Review> schemasToReviews(Iterable<ReviewMongoSchema> reviews) {
        return StreamSupport
            .stream(reviews.spliterator(), false)
            .map(this::schemaToReview)
            .collect(Collectors.toList());
    }
}
