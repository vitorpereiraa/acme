package com.isep.acme.persistence.mongo;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.mongo.ProductMongoSchema;
import com.isep.acme.schemas.mongo.ReviewMongoSchema;
import com.isep.acme.schemas.mongo.UserMongoSchema;

@Profile("mongo")
@Repository
public interface ReviewMongoPersistence extends MongoRepository<ReviewMongoSchema, Long> {
    
    @Query(value = "{product :?0}", sort = "{publishingDate:-1}")  
    List<ReviewMongoSchema> findByProductId(ProductMongoSchema product);

    @Query("{approvalStatus : ?0}")  
    List<ReviewMongoSchema> findReviewsByStatus(String status);

    @Query(value = "{$and :[{product: ?0},{status: ?1}]}", sort ="{publishingDate:-1}")   
    List<ReviewMongoSchema> findByProductIdStatus(ProductMongoSchema product, String status);

    @Query(value = "{user :?0}", sort = "{publishingDate:-1}")  
    List<ReviewMongoSchema> findByUserId(UserMongoSchema user);
}