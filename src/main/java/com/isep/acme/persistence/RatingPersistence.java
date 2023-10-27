package com.isep.acme.persistence;

import com.isep.acme.dataModels.sql.RatingDataModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RatingPersistence extends CrudRepository<RatingDataModel, Long> {

    @Query("SELECT r FROM Rating r WHERE r.rate=:rate")
    Optional<RatingDataModel> findByRate(Double rate);

}
