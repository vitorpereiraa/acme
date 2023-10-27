package com.isep.acme.repositories;

import com.isep.acme.dataModels.ProductMapper;
import com.isep.acme.dataModels.RatingDataModel;
import com.isep.acme.dataModels.RatingMapper;
import com.isep.acme.model.Rating;
import com.isep.acme.persistence.RatingPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RatingRepositoryImpl implements RatingRepository{

    @Autowired
    private RatingPersistence ratingPersistence;

    @Override
    public Optional<Rating> findByRate(Double rate) {
        Optional<RatingDataModel> ratingDataModel = ratingPersistence.findByRate(rate);
        return ratingDataModel.map(RatingMapper::DataModelToDomain);
    }

    @Override
    public Rating save(Rating rating) {
        RatingDataModel dataModel = RatingMapper.DomainToDataModel(rating);
        RatingDataModel ratingDataModel = ratingPersistence.save(dataModel);
        return RatingMapper.DataModelToDomain(ratingDataModel);
    }
}
