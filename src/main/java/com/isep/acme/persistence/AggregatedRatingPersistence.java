package com.isep.acme.persistence;

import com.isep.acme.dataModels.sql.AggregatedRatingDataModel;
import com.isep.acme.dataModels.sql.ProductDataModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AggregatedRatingPersistence extends CrudRepository<AggregatedRatingDataModel, Long> {

    @Query("SELECT a FROM AggregatedRating a WHERE a.product=:product")
    Optional<AggregatedRatingDataModel> findByProductId(ProductDataModel product);

}
