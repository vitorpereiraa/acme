package com.isep.acme.schemas.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Rating;

@Profile("mongo")
@Component
public class RatingMongoMapper {
    
    public Rating schemaToRating(RatingMongoSchema schema){
        return new Rating(schema.getRate());
    }

    public RatingMongoSchema ratingToSchema(Rating rating) {
        return new RatingMongoSchema(rating.getIdRating(),rating.getVersion(),rating.getRate());
    }
}
