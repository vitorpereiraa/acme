package com.isep.acme.persistence.mongo;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.mongo.ProductMongoSchema;

@Profile("mongo")
@Repository
public interface ProductMongoPersistence extends MongoRepository<ProductMongoSchema, Long> {

    List<ProductMongoSchema> findByDesignation(String designation);

    Optional<ProductMongoSchema> findBySku(String sku);
    
    Long deleteBySku(String sku);

    // @Query("UPDATE ProductMongoSchema p SET p.sku = :sku WHERE p.sku=:sku")
    // ProductMongoSchema updateBySku(String sku);
}
