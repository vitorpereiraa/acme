package com.isep.acme.services.reviewRecommendationAlgos;

import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.services.iRepositories.ReviewRepository;
import com.isep.acme.services.iRepositories.UserRepository;
import com.isep.acme.services.iServices.ReviewRecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ReviewRecommendationsAlgo2 implements ReviewRecommendationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getRecommendations(List<Review> allReviews, Long userId) {
        List<Review> recommendedReviews = new ArrayList<>();
        AtomicInteger currentUserTotalVotes = new AtomicInteger();
        Map<Long, Integer> votesPerUser = new TreeMap<>();

        allReviews.forEach(review -> {
            currentUserTotalVotes.addAndGet(GetUsersWithSameVotes(review.getUpVote(), userId, votesPerUser));
            currentUserTotalVotes.addAndGet(GetUsersWithSameVotes(review.getDownVote(), userId, votesPerUser));
        });

        for (var entry : votesPerUser.entrySet()) {
            if (((float) entry.getValue()) / currentUserTotalVotes.get() >= 0.5) {
                var user = userRepository.findById(entry.getKey());
                if (user.isPresent()) {
                    var reviews = reviewRepository.findByUserId(user.get());
                    recommendedReviews.addAll(reviews);
                }
            }
        }

        return recommendedReviews;
    }

    private int GetUsersWithSameVotes(List<Vote> votes, Long currentUserId, Map<Long, Integer> votesPerUser) {
        if (votes.stream().anyMatch(vote -> vote.getUserID().equals(currentUserId))) {
            votes.forEach(vote -> {
                if (votesPerUser.containsKey(vote.getUserID())) {
                    votesPerUser.replace(vote.getUserID(), votesPerUser.get(vote.getUserID()) + 1);
                } else {
                    votesPerUser.put(vote.getUserID(), 1);
                }
            });

            return 1;
        }

        return 0;
    }
}
