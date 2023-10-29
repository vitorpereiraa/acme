package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.persistence.neo4j.AggregatedRatingNeo4jPersistence;
import com.isep.acme.schemas.neo4j.AggregatedRatingNeo4jMapper;
import com.isep.acme.schemas.neo4j.AggregatedRatingNeo4jjSchema;
import com.isep.acme.schemas.neo4j.ProductNeo4jMapper;
import com.isep.acme.schemas.neo4j.ProductNeo4jSchema;
import com.isep.acme.services.iRepositories.AggregatedRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AggregatedRatingNeo4jRepositoryImpl implements AggregatedRatingRepository {

    @Autowired
    private AggregatedRatingNeo4jPersistence aggregatedRatingPersistence;

    @Autowired
    private AggregatedRatingNeo4jMapper aggregatedRatingMapper;

    @Autowired
    private ProductNeo4jMapper productMapper;

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        ProductNeo4jSchema dataModel = productMapper.domainToSchema(product);
        Optional<AggregatedRatingNeo4jjSchema> aggregatedRatingDataModel = aggregatedRatingPersistence.findByProductId(dataModel);
        return aggregatedRatingDataModel.map(aggregatedRatingMapper::schemaToDomain);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        AggregatedRatingNeo4jjSchema dataModel = aggregatedRatingMapper.domainToSchema(aggregatedRating);
        AggregatedRatingNeo4jjSchema aggregatedRatingDataModel = aggregatedRatingPersistence.save(dataModel);
        return aggregatedRatingMapper.schemaToDomain(aggregatedRatingDataModel);
    }
}
