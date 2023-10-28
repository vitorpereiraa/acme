package com.isep.acme.persistence.sql;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.isep.acme.schemas.sql.ImageSqlSchema;

@Profile("sql")
@Repository
public interface ImageSqlPersistence extends CrudRepository<ImageSqlSchema, Long> {
}
