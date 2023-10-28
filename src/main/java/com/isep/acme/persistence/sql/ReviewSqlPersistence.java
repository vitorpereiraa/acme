package com.isep.acme.persistence.sql;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.sql.ProductSqlSchema;
import com.isep.acme.schemas.sql.ReviewSqlSchema;
import com.isep.acme.schemas.sql.UserSqlSchema;

@Profile("sql")
@Repository
public interface ReviewSqlPersistence extends CrudRepository<ReviewSqlSchema, Long> {

    //Optional<Review> findById(Long productId);

    @Query("SELECT r FROM ReviewSqlSchema r WHERE r.product=:product ORDER BY r.publishingDate DESC")
    List<ReviewSqlSchema> findByProductId(ProductSqlSchema product);

    @Query("SELECT r FROM ReviewSqlSchema r WHERE r.approvalStatus='pending'")
    List<ReviewSqlSchema> findPendingReviews();

    @Query("SELECT r FROM ReviewSqlSchema r WHERE r.approvalStatus='active'")
    List<ReviewSqlSchema> findActiveReviews();

    @Query("SELECT r FROM ReviewSqlSchema r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate DESC")
    List<ReviewSqlSchema> findByProductIdStatus(ProductSqlSchema product, String status);

    @Query("SELECT r FROM ReviewSqlSchema r WHERE r.user=:user ORDER BY r.publishingDate DESC")
    List<ReviewSqlSchema> findByUserId(UserSqlSchema user);
}
