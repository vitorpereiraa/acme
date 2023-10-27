package com.isep.acme.repositories;

import com.isep.acme.dataModels.*;
import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.persistence.ReviewPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    @Autowired
    private ReviewPersistence reviewPersistence;

    @Override
    public Optional<List<Review>> findByProductId(Product product) {
        ProductDataModel productDataModel = ProductMapper.DomainToDataModel(product);
        Optional<List<ReviewDataModel>> reviewDataModelList = reviewPersistence.findByProductId(productDataModel);
        return reviewDataModelList.map(ReviewMapper::DataModelListToDomainList);
    }

    @Override
    public Optional<List<Review>> findPendingReviews() {
        Optional<List<ReviewDataModel>> reviewDataModelList = reviewPersistence.findPendingReviews();
        return reviewDataModelList.map(ReviewMapper::DataModelListToDomainList);
    }

    @Override
    public Optional<List<Review>> findActiveReviews() {
        Optional<List<ReviewDataModel>> reviewDataModelList = reviewPersistence.findActiveReviews();
        return reviewDataModelList.map(ReviewMapper::DataModelListToDomainList);
    }

    @Override
    public Optional<List<Review>> findByProductIdStatus(Product product, String status) {
        ProductDataModel productDataModel = ProductMapper.DomainToDataModel(product);
        Optional<List<ReviewDataModel>> reviewDataModelList = reviewPersistence.findByProductIdStatus(productDataModel, status);
        return reviewDataModelList.map(ReviewMapper::DataModelListToDomainList);
    }

    @Override
    public Optional<List<Review>> findByUserId(User user) {
        UserDataModel userDataModel = UserMapper.DomainToDataModel(user);
        Optional<List<ReviewDataModel>> reviewDataModelList = reviewPersistence.findByUserId(userDataModel);
        return reviewDataModelList.map(ReviewMapper::DataModelListToDomainList);
    }

    @Override
    public Review save(Review review) {
        ReviewDataModel dataModel = ReviewMapper.DomainToDataModel(review);
        ReviewDataModel reviewDataModel = reviewPersistence.save(dataModel);
        return ReviewMapper.DataModelToDomain(reviewDataModel);
    }

    @Override
    public Optional<Review> findById(Long id) {
        Optional<ReviewDataModel> reviewDataModel = reviewPersistence.findById(id);
        return reviewDataModel.map(ReviewMapper::DataModelToDomain);
    }

    @Override
    public Iterable<Review> findAll() {
        Iterable<ReviewDataModel> reviewDataModels = reviewPersistence.findAll();
        return ReviewMapper.DataModelListToDomainList(reviewDataModels);
    }

    @Override
    public void delete(Review review) {
        ReviewDataModel dataModel = ReviewMapper.DomainToDataModel(review);
        reviewPersistence.delete(dataModel);
    }
}
