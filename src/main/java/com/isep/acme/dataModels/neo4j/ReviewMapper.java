package com.isep.acme.dataModels.neo4j;

import com.isep.acme.model.Review;

public class ReviewMapper {
    public static Review SchemaToDomain(ReviewDataModel schema){
        return new Review(schema.getIdReview(), schema.getVersion(), schema.getApprovalStatus(), schema.getReviewText(),
                schema.getUpVote(), schema.getDownVote(),schema.getReport(),schema.getPublishingDate(), schema.getFunFact(),
                schema.getProduct(), schema.getRating(), schema.getUser());
    }

    public static ReviewDataModel DomainToSchema(Review review){
        return new ReviewDataModel(review.getIdReview(), review.getVersion(), review.getApprovalStatus(), review.getReviewText(),
                review.getUpVote(), review.getDownVote(),review.getReport(),review.getPublishingDate(), review.getFunFact(),
                review.getProduct(), review.getRating(), review.getUser());
    }
}
