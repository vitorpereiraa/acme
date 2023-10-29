package com.isep.acme.dtos;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.isep.acme.model.Review;

public class ReviewMapper {

    public static ReviewDTO toDto(Review review){
        return new ReviewDTO(review.getIdReview(), review.getReviewText(), review.getPublishingDate(), review.getApprovalStatus(), review.getFunFact(), review.getRating().getRate(), review.getUpVote().size());
    }

    public static List<ReviewDTO> toDtoList(Iterable<Review> review) {
        return StreamSupport
            .stream(review.spliterator(), false)
            .map(ReviewMapper::toDto)
            .collect(Collectors.toList());
    }
}
