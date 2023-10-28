package com.isep.acme.persistence.mongo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.mongo.AggregatedRatingMongoSchema;
import com.isep.acme.schemas.mongo.ProductMongoSchema;

@Profile("mongo")
@Repository
public interface AggregatedRatingMongoPersistence extends MongoRepository<AggregatedRatingMongoSchema, Long> {
    
    @Query("{product :?0}")  
    Optional<AggregatedRatingMongoSchema> findByProductId(ProductMongoSchema product);
}
