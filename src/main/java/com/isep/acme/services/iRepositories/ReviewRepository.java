package com.isep.acme.services.iRepositories;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    Review save(Review review);

    void delete(Review review);

    List<Review> findAll();

    Optional<Review> findById(Long productId);

    List<Review> findByProductId(Product product);

    List<Review> findPendingReviews();

    List<Review> findActiveReviews();

    List<Review> findByProductIdStatus(Product product, String status);

    List<Review> findByUserId(User user);
}
