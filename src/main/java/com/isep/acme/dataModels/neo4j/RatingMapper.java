package com.isep.acme.dataModels.neo4j;

import com.isep.acme.model.Rating;

public class RatingMapper {
    public static Rating SchemaToDomain(RatingDataModel schema){
        return new Rating(schema.getIdRating(), schema.getVersion(), schema.getRate());
    }

    public static RatingDataModel DomainToSchema(Rating rating){
        return new RatingDataModel(rating.getIdRating(), rating.getVersion(), rating.getRate());
    }
}
