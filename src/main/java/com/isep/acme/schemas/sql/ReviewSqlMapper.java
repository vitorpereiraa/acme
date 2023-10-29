package com.isep.acme.schemas.sql;

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

@Profile("sql")
@Component
public class ReviewSqlMapper {

    @Autowired
    ProductSqlMapper productMapper;

    @Autowired
    RatingSqlMapper ratingMapper;

    @Autowired
    UserSqlMapper userMapper;

    @Autowired
    VotesSqlMapper votesMapper;

    public Review schemaToReview(ReviewSqlSchema schema){
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

    public ReviewSqlSchema reviewToSchema (Review review){
        ProductSqlSchema product = productMapper.productToSchema(review.getProduct());
        RatingSqlSchema rating = ratingMapper.ratingToSchema(review.getRating());
        UserSqlSchema user = userMapper.userToSchema(review.getUser());
        List<VoteSqlSchema> upVotes = votesMapper.votesToSchemas(review.getUpVote());
        List<VoteSqlSchema> downVotes = votesMapper.votesToSchemas(review.getDownVote());
        return new ReviewSqlSchema(
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

    public List<Review> schemasToReviews(Iterable<ReviewSqlSchema> reviews) {
        return StreamSupport
            .stream(reviews.spliterator(), false)
            .map(this::schemaToReview)
            .collect(Collectors.toList());
    }
}
