package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.services.iRepositories.AggregatedRatingRepository;

@Profile("mongo")
@Component
public class AggregatedRatingMongoRepository implements AggregatedRatingRepository {

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByProductId'");
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
