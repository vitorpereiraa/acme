package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.persistence.mongo.AggregatedRatingMongoPersistence;
import com.isep.acme.schemas.mongo.AggregatedRatingMongoMapper;
import com.isep.acme.schemas.mongo.AggregatedRatingMongoSchema;
import com.isep.acme.schemas.mongo.ProductMongoMapper;
import com.isep.acme.schemas.mongo.ProductMongoSchema;
import com.isep.acme.services.iRepositories.AggregatedRatingRepository;

@Profile("mongo")
@Component
public class AggregatedRatingMongoRepository implements AggregatedRatingRepository {

    @Autowired
    AggregatedRatingMongoPersistence aggRatingRelationalPersistence;

    @Autowired
    DatabaseSequenceRepository sequenceGenerator;

    @Autowired
    ProductMongoMapper productMapper;

    @Autowired
    AggregatedRatingMongoMapper aggRatingMapper;

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        ProductMongoSchema productSchema = productMapper.productToSchema(product);
        Optional<AggregatedRatingMongoSchema> aggSchema = aggRatingRelationalPersistence.findByProductId(productSchema);
        if (aggSchema.isEmpty()) return Optional.empty();
        AggregatedRating aggRating = aggRatingMapper.schemaToAggregatedRating(aggSchema.get());
        return Optional.of(aggRating);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        AggregatedRatingMongoSchema schema = aggRatingMapper.aggregatedRatingToSchema(aggregatedRating);
        if(aggregatedRating.getAggregatedId() == null){
            schema.setAggregatedId(sequenceGenerator.generateSequence(AggregatedRatingMongoSchema.SEQUENCE_NAME));
        }
        AggregatedRatingMongoSchema persistedAggRating = aggRatingRelationalPersistence.save(schema);
        return aggRatingMapper.schemaToAggregatedRating(persistedAggRating);
    }
}
