package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ReviewNeo4jMapper {

    @Autowired
    private UserNeo4jMapper userMapper;

    @Autowired
    private VoteNeo4jMapper voteMapper;

    @Autowired
    private ProductNeo4jMapper productMapper;

    @Autowired
    private RatingNeo4jMapper ratingMapper;

    public Review schemaToDomain(ReviewNeo4jSchema schema){
        var user = userMapper.schemaToDomain(schema.getUser());
        var upVotes = voteMapper.schemaListToDomainList(schema.getUpVote());
        var downVotes = voteMapper.schemaListToDomainList(schema.getDownVote());
        var rating = ratingMapper.schemaToDomain(schema.getRating());
        var product = productMapper.schemaToDomain(schema.getProduct());
        return new Review(schema.getIdReview(), schema.getVersion(), schema.getApprovalStatus(), schema.getReviewText(),
                upVotes, downVotes,schema.getReport(),schema.getPublishingDate(), schema.getFunFact(),
                product, rating, user);
    }

    public ReviewNeo4jSchema domainToSchema(Review review){
        var userSchema = userMapper.domainToSchema(review.getUser());
        var upVoteSchemas = voteMapper.domainListToSchemaList(review.getUpVote());
        var downVoteSchemas = voteMapper.domainListToSchemaList(review.getDownVote());
        var ratingSchema = ratingMapper.domainToSchema(review.getRating());
        var productSchema = productMapper.domainToSchema(review.getProduct());
        return new ReviewNeo4jSchema(review.getIdReview(), review.getVersion(), review.getApprovalStatus(), review.getReviewText(),
                upVoteSchemas, downVoteSchemas,review.getReport(),review.getPublishingDate(), review.getFunFact(),
                productSchema, ratingSchema, userSchema);
    }

    public List<Review> schemaListToDomainList(Iterable<ReviewNeo4jSchema> schemas){
        return StreamSupport
                .stream(schemas.spliterator(), false)
                .map(this::schemaToDomain)
                .collect(Collectors.toList());
    }

    public List<ReviewNeo4jSchema> domainListToSchemaList(Iterable<Review> domains){
        return StreamSupport
                .stream(domains.spliterator(), false)
                .map(this::domainToSchema)
                .collect(Collectors.toList());
    }
}
