package com.isep.acme.repositories.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.services.iRepositories.ReviewRepository;

@Profile("mongo")
@Component
public class ReviewMongoRepository implements ReviewRepository {

    @Override
    public Review save(Review review) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public void delete(Review review) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Review> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<Review> findById(Long productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Review> findByProductId(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByProductId'");
    }

    @Override
    public List<Review> findPendingReviews() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPendingReviews'");
    }

    @Override
    public List<Review> findActiveReviews() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findActiveReviews'");
    }

    @Override
    public List<Review> findByProductIdStatus(Product product, String status) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByProductIdStatus'");
    }

    @Override
    public List<Review> findByUserId(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserId'");
    }
}
