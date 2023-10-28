package com.isep.acme.repositories.sql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.persistence.sql.AggregatedRatingSqlPersistence;
import com.isep.acme.schemas.sql.AggregatedRatingSqlMapper;
import com.isep.acme.schemas.sql.AggregatedRatingSqlSchema;
import com.isep.acme.schemas.sql.ProductSqlMapper;
import com.isep.acme.schemas.sql.ProductSqlSchema;
import com.isep.acme.services.iRepositories.AggregatedRatingRepository;

@Profile("sql")
@Component
public class AggregatedRatingSqlRepository implements AggregatedRatingRepository {

    @Autowired
    AggregatedRatingSqlPersistence aggRatingRelationalPersistence;

    @Autowired
    ProductSqlMapper productMapper;

    @Autowired
    AggregatedRatingSqlMapper aggRatingMapper;

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        ProductSqlSchema productSchema = productMapper.productToSchema(product);
        Optional<AggregatedRatingSqlSchema> aggSchema = aggRatingRelationalPersistence.findByProductId(productSchema);
        if (aggSchema.isEmpty()) return Optional.empty();
        AggregatedRating aggRating = aggRatingMapper.schemaToAggregatedRating(aggSchema.get());
        return Optional.of(aggRating);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        var schema = aggRatingMapper.aggregatedRatingToSchema(aggregatedRating);
        var persistedAggRating = aggRatingRelationalPersistence.save(schema);
        return aggRatingMapper.schemaToAggregatedRating(persistedAggRating);
    }
}
