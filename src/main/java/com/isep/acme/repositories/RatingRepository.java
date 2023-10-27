package com.isep.acme.repositories;

import com.isep.acme.model.Rating;

import java.util.Optional;

public interface RatingRepository {
    Optional<Rating> findByRate(Double rate);

    Rating save(Rating rating);
}
