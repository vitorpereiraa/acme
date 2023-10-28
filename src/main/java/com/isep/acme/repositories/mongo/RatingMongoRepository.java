package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Rating;
import com.isep.acme.persistence.mongo.RatingMongoPersistence;
import com.isep.acme.schemas.mongo.RatingMongoMapper;
import com.isep.acme.schemas.mongo.RatingMongoSchema;
import com.isep.acme.services.iRepositories.RatingRepository;

@Profile("mongo")
@Component
public class RatingMongoRepository implements RatingRepository {

    @Autowired
    RatingMongoPersistence ratingPersistence;

    @Autowired
    DatabaseSequenceRepository sequenceGenerator;

    @Autowired
    RatingMongoMapper ratingMapper;

    @Override
    public Optional<Rating> findByRate(Double rate) {
        Optional<RatingMongoSchema> schema = ratingPersistence.findByRate(rate);
        if(schema.isEmpty()) return Optional.empty();
        Rating rating = ratingMapper.schemaToRating(schema.get());
        return Optional.of(rating);
    }

    @Override
    public Rating save(Rating rating) {
        RatingMongoSchema schema = ratingMapper.ratingToSchema(rating); 
        if(schema.getIdRating() == null){
            schema.setIdRating(sequenceGenerator.generateSequence(RatingMongoSchema.SEQUENCE_NAME));
        }
        RatingMongoSchema persisted = ratingPersistence.save(schema);
        return ratingMapper.schemaToRating(persisted);
    }
}
