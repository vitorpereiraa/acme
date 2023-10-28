package com.isep.acme.persistence.sql;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.sql.RatingSqlSchema;

import java.util.Optional;

@Profile("sql")
@Repository
public interface RatingSqlPersistence extends CrudRepository<RatingSqlSchema, Long> {

    @Query("SELECT r FROM RatingSqlSchema r WHERE r.rate=:rate")
    Optional<RatingSqlSchema> findByRate(Double rate);

}
