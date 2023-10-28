package com.isep.acme.repositories.sql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Rating;
import com.isep.acme.persistence.sql.RatingSqlPersistence;
import com.isep.acme.schemas.sql.RatingSqlMapper;
import com.isep.acme.schemas.sql.RatingSqlSchema;
import com.isep.acme.services.iRepositories.RatingRepository;


@Profile("sql")
@Component
public class RatingSqlRepository implements RatingRepository {

    @Autowired
    RatingSqlPersistence ratingPersistence;

    @Autowired
    RatingSqlMapper ratingMapper;

    @Override
    public Optional<Rating> findByRate(Double rate) {
        Optional<RatingSqlSchema> schema = ratingPersistence.findByRate(rate);
        if(schema.isEmpty()) return Optional.empty();
        Rating rating = ratingMapper.schemaToRating(schema.get());
        return Optional.of(rating);
    }

    @Override
    public Rating save(Rating rating) {
        RatingSqlSchema schema = ratingMapper.ratingToSchema(rating); 
        RatingSqlSchema persisted = ratingPersistence.save(schema);
        return ratingMapper.schemaToRating(persisted);
    }
}
