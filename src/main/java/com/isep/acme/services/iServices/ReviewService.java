package com.isep.acme.services.iServices;

import java.util.List;

import com.isep.acme.dtos.CreateReviewDTO;
import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.VoteReviewDTO;
import com.isep.acme.model.*;

public interface ReviewService {

    Iterable<Review> getAll();

    List<ReviewDTO> getReviewsOfProduct(String sku, String status);

    ReviewDTO create(CreateReviewDTO createReviewDTO, String sku);

    boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO);

    Double getWeightedAverage(Product product);

    Boolean DeleteReview(Long reviewId);

    List<ReviewDTO> findPendingReview();

    ReviewDTO moderateReview(Long reviewID, String approved);

    List<ReviewDTO> findReviewsByUser(Long userID);

    List<ReviewDTO> getReviewRecommendations(Long userId);
}
