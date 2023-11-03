package com.isep.acme.services;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.dtos.CreateReviewDTO;
import com.isep.acme.dtos.ReviewDTO;
import com.isep.acme.dtos.ReviewMapper;
import com.isep.acme.dtos.VoteReviewDTO;

import java.lang.IllegalArgumentException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.isep.acme.model.*;
import com.isep.acme.services.iRepositories.ProductRepository;
import com.isep.acme.services.iRepositories.ReviewRepository;
import com.isep.acme.services.iRepositories.UserRepository;
import com.isep.acme.services.iServices.RatingService;
import com.isep.acme.services.iServices.ReviewRecommendationService;
import com.isep.acme.services.iServices.ReviewService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewServiceImpl.class);

    @Autowired
    ReviewRepository repository;

    @Autowired
    ProductRepository pRepository;

    @Autowired
    UserRepository uRepository;

    @Autowired
    UserService userService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RestService restService;

    @Autowired
    @Qualifier("reviewRecommendationAlgorithm")
    ReviewRecommendationService recommendationService;

    @Override
    public Iterable<Review> getAll() {
        return repository.findAll();
    }

    @Override
    public ReviewDTO create(final CreateReviewDTO createReviewDTO, String sku) {

        final Optional<Product> product = pRepository.findBySku(sku);

        if (product.isEmpty())
            return null;

        final var user = userService.getUserId(createReviewDTO.getUserID());

        if (user.isEmpty())
            return null;

        Rating rating = null;
        Optional<Rating> r = ratingService.findByRate(createReviewDTO.getRating());
        if (r.isPresent()) {
            rating = r.get();
        }

        LocalDate date = LocalDate.now();

        String funfact = restService.getFunFact(date);

        if (funfact == null)
            return null;

        Review review = new Review(createReviewDTO.getReviewText(), date, product.get(), funfact, rating, user.get());

        review = repository.save(review);

        if (review == null)
            return null;

        return ReviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDTO> getReviewsOfProduct(String sku, String status) {

        Optional<Product> product = pRepository.findBySku(sku);
        if (product.isEmpty())
            return null;

        Iterable<Review> r = repository.findByProductIdStatus(product.get(), status);

        return ReviewMapper.toDtoList(r);
    }

    @Override
    public boolean addVoteToReview(Long reviewID, VoteReviewDTO voteReviewDTO) {

        Optional<Review> review = this.repository.findById(reviewID);

        if (review.isEmpty())
            return false;

        Vote vote = new Vote(voteReviewDTO.getVote(), voteReviewDTO.getUserID());
        if (voteReviewDTO.getVote().equalsIgnoreCase("upVote")) {
            boolean added = review.get().addUpVote(vote);
            if (added) {
                Review reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        } else if (voteReviewDTO.getVote().equalsIgnoreCase("downVote")) {
            boolean added = review.get().addDownVote(vote);
            if (added) {
                Review reviewUpdated = this.repository.save(review.get());
                return reviewUpdated != null;
            }
        }
        return false;
    }

    @Override
    public Double getWeightedAverage(Product product) {

        List<Review> r = repository.findByProductId(product);

        if (r.isEmpty())
            return 0.0;

        double sum = 0;

        for (Review rev : r) {
            Rating rate = rev.getRating();

            if (rate != null) {
                sum += rate.getRate();
            }
        }

        return sum / r.size();
    }

    @Override
    public Boolean DeleteReview(Long reviewId) {

        Optional<Review> rev = repository.findById(reviewId);

        if (rev.isEmpty()) {
            return null;
        }
        Review r = rev.get();

        if (r.getUpVote().isEmpty() && r.getDownVote().isEmpty()) {
            repository.delete(r);
            return true;
        }
        return false;
    }

    @Override
    public List<ReviewDTO> findPendingReview() {

        List<Review> r = repository.findPendingReviews();

        return ReviewMapper.toDtoList(r);
    }

    @Override
    public ReviewDTO moderateReview(Long reviewID, String approved)
            throws ResourceNotFoundException, IllegalArgumentException {

        Optional<Review> r = repository.findById(reviewID);

        if (r.isEmpty()) {
            throw new ResourceNotFoundException("Review not found");
        }

        Boolean ap = r.get().setApprovalStatus(approved);

        if (!ap) {
            throw new IllegalArgumentException("Invalid status value");
        }

        Review review = repository.save(r.get());

        return ReviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDTO> findReviewsByUser(Long userID) {

        final Optional<User> user = uRepository.findById(userID);

        if (user.isEmpty())
            return null;

        List<Review> r = repository.findByUserId(user.get());

        return ReviewMapper.toDtoList(r);
    }

    @Override
    public List<ReviewDTO> getReviewRecommendations() {
        final List<Review> reviews = repository.findAll();
        final List<Review> recommendations = recommendationService.getRecommendations(reviews);
        return ReviewMapper.toDtoList(recommendations);
    }
}