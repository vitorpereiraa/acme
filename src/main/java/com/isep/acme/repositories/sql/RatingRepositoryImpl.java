package com.isep.acme.repositories.sql;

import com.isep.acme.dataModels.sql.RatingDataModel;
import com.isep.acme.dataModels.sql.RatingMapper;
import com.isep.acme.model.Rating;
import com.isep.acme.persistence.sql.RatingPersistence;
import com.isep.acme.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RatingRepositoryImpl implements RatingRepository {

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
