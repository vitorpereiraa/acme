package com.isep.acme.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.isep.acme.services.iServices.ReviewRecommendationService;

@Component
public class ReviewRecommendationsAlgo1 implements ReviewRecommendationService {

    @Override
    public List<Review> getRecommendations(List<Review> allReviews) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecommendations'");
    }
}
