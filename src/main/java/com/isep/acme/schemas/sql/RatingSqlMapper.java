package com.isep.acme.schemas.sql;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Rating;

@Profile("sql")
@Component
public class RatingSqlMapper {

    public Rating schemaToRating(RatingSqlSchema schema){
        return new Rating(schema.getRate());
    }

    public RatingSqlSchema ratingToSchema(Rating rating) {
        return new RatingSqlSchema(rating.getIdRating(),rating.getVersion(),rating.getRate());
    }
}
