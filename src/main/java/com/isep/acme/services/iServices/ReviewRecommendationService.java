package com.isep.acme.services.iServices;

import java.util.List;

import com.isep.acme.model.Review;

public interface ReviewRecommendationService {
    List<Review> getRecommendations(List<Review> allReviews);
}
