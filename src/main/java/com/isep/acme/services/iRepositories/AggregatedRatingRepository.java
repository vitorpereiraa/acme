package com.isep.acme.services.iRepositories;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import java.util.Optional;

public interface AggregatedRatingRepository {
    Optional<AggregatedRating> findByProductId(Product product);
    AggregatedRating save(AggregatedRating aggregatedRating);
}
