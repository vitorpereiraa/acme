package com.isep.acme.repositories;

import com.isep.acme.dataModels.sql.AggregatedRatingDataModel;
import com.isep.acme.dataModels.sql.AggregatedRatingMapper;
import com.isep.acme.dataModels.sql.ProductDataModel;
import com.isep.acme.dataModels.sql.ProductMapper;
import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.persistence.AggregatedRatingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AggregatedRatingRepositoryImpl implements AggregatedRatingRepository {

    @Autowired
    private AggregatedRatingPersistence aggregatedRatingPersistence;

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        ProductDataModel productDataModel= ProductMapper.DomainToDataModel(product);
        Optional<AggregatedRatingDataModel> dataModel = aggregatedRatingPersistence.findByProductId(productDataModel);
        return dataModel.map(AggregatedRatingMapper::DataModelToDomain);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        AggregatedRatingDataModel dataModel = AggregatedRatingMapper.DomainToDataModel(aggregatedRating);
        AggregatedRatingDataModel aggregatedRatingDataModel = aggregatedRatingPersistence.save(dataModel);
        return AggregatedRatingMapper.DataModelToDomain(aggregatedRatingDataModel);
    }
}
