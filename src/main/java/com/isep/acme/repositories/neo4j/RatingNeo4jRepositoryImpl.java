package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.Rating;
import com.isep.acme.persistence.neo4j.RatingNeo4jPersistence;
import com.isep.acme.schemas.neo4j.RatingNeo4jMapper;
import com.isep.acme.schemas.neo4j.RatingNeo4jSchema;
import com.isep.acme.services.iRepositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RatingNeo4jRepositoryImpl implements RatingRepository {

    @Autowired
    private RatingNeo4jPersistence ratingPersistence;

    @Autowired
    private RatingNeo4jMapper ratingMapper;
    @Override
    public Optional<Rating> findByRate(Double rate) {
        Optional<RatingNeo4jSchema> ratingDataModel = ratingPersistence.findByRate(rate);
        return ratingDataModel.map(ratingMapper::schemaToDomain);
    }

    @Override
    public Rating save(Rating rating) {
        RatingNeo4jSchema dataModel= ratingMapper.domainToSchema(rating);
        RatingNeo4jSchema ratingDataModel = ratingPersistence.save(dataModel);
        return ratingMapper.schemaToDomain(ratingDataModel);
    }
}
