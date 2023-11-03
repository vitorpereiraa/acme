package com.isep.acme.schemas.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;

@Profile("mongo")
@Component
public class AggregatedRatingMongoMapper {

    @Autowired
    ProductMongoMapper productMapper;

    public AggregatedRatingMongoSchema aggregatedRatingToSchema(AggregatedRating aggRating){
        ProductMongoSchema productSchema = productMapper.productToSchema(aggRating.getProduct());
        return new AggregatedRatingMongoSchema(aggRating.getAggregatedId(),aggRating.getAverage(),productSchema);
    }

    public AggregatedRating schemaToAggregatedRating (AggregatedRatingMongoSchema schema){
        Product product = productMapper.schemaToProduct(schema.getProduct());
        return new AggregatedRating(schema.getAggregatedId(),schema.getAverage(),product);
    }
    
}
