package com.isep.acme.persistence.sql;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.sql.AggregatedRatingSqlSchema;
import com.isep.acme.schemas.sql.ProductSqlSchema;

import java.util.Optional;

@Profile("sql")
@Repository
public interface AggregatedRatingSqlPersistence extends CrudRepository<AggregatedRatingSqlSchema, Long> {

    @Query("SELECT a FROM AggregatedRatingSqlSchema a WHERE a.product=:product")
    Optional<AggregatedRatingSqlSchema> findByProductId(ProductSqlSchema product);

}
