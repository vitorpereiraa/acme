package com.isep.acme.services.reviewRecommendationAlgos;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.isep.acme.model.Review;
import org.springframework.stereotype.Component;

import com.isep.acme.services.iServices.ReviewRecommendationService;

@Component
public class ReviewRecommendationsAlgo1 implements ReviewRecommendationService {

    @Override
    public List<Review> getRecommendations(List<Review> allReviews, Long userId) {
        Comparator<Review> reviewQtyOfUpVotesComparator = new Comparator<Review>() {
            @Override
            public int compare(Review r1, Review r2) {
                Integer sizeR1 = r1.getUpVote().size();
                Integer sizeR2 = r2.getUpVote().size();
                return sizeR1.compareTo(sizeR2);
            }
        };

        return allReviews
                .stream()
                .filter(r -> r.getUpVote().size() > 4)
                .filter(r -> (r.getUpVote().size() / (r.getUpVote().size() + r.getDownVote().size())) > 0.65)
                .sorted(reviewQtyOfUpVotesComparator)
                .collect(Collectors.toList());
    }
}
