package com.isep.acme.schemas.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;

@Profile("sql")
@Component
public class AggregatedRatingSqlMapper {

    @Autowired
    ProductSqlMapper productMapper;

    public AggregatedRatingSqlSchema aggregatedRatingToSchema(AggregatedRating aggRating){
        ProductSqlSchema productSchema = productMapper.productToSchema(aggRating.getProduct());
        return new AggregatedRatingSqlSchema(aggRating.getAggregatedId(),aggRating.getAverage(),productSchema);
    }

    public AggregatedRating schemaToAggregatedRating (AggregatedRatingSqlSchema schema){
        Product product = productMapper.schemaToProduct(schema.getProduct());
        return new AggregatedRating(schema.getAggregatedId(),schema.getAverage(),product);
    }
}
