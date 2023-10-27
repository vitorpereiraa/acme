package com.isep.acme.persistence.sql;

import com.isep.acme.dataModels.sql.ProductDataModel;
import com.isep.acme.dataModels.sql.ReviewDataModel;
import com.isep.acme.dataModels.sql.UserDataModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewPersistence extends CrudRepository<ReviewDataModel, Long> {

    //Optional<Review> findById(Long productId);

    @Query("SELECT r FROM Review r WHERE r.product=:product ORDER BY r.publishingDate DESC")
    Optional<List<ReviewDataModel>> findByProductId(ProductDataModel product);


    @Query("SELECT r FROM Review r WHERE r.approvalStatus='pending'")
    Optional<List<ReviewDataModel>> findPendingReviews();

    @Query("SELECT r FROM Review r WHERE r.approvalStatus='active'")
    Optional<List<ReviewDataModel>> findActiveReviews();

    @Query("SELECT r FROM Review r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate DESC")
    Optional<List<ReviewDataModel>> findByProductIdStatus(ProductDataModel product, String status);

    @Query("SELECT r FROM Review r WHERE r.user=:user ORDER BY r.publishingDate DESC")
    Optional<List<ReviewDataModel>> findByUserId(UserDataModel user);
}
