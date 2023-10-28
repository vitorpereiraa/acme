package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Rating;
import com.isep.acme.services.iRepositories.RatingRepository;

@Profile("mongo")
@Component
public class RatingMongoRepository implements RatingRepository {

    @Override
    public Optional<Rating> findByRate(Double rate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByRate'");
    }

    @Override
    public Rating save(Rating rating) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
