package com.isep.acme.repositories.sql;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.ProdImage;
import com.isep.acme.persistence.sql.ImageSqlPersistence;
import com.isep.acme.schemas.sql.ImageSqlMapper;
import com.isep.acme.schemas.sql.ImageSqlSchema;
import com.isep.acme.services.iRepositories.ImageRepository;

@Profile("sql")
@Component
public class ImageSqlRepository implements ImageRepository {

    @Autowired
    ImageSqlPersistence imagePersistence;

    @Autowired
    ImageSqlMapper imageMapper;

    @Override
    public Iterable<ProdImage> findAll() {
        Iterable<ImageSqlSchema> schemas = imagePersistence.findAll();
        return imageMapper.schemasToImages(schemas);
    }

    @Override
    public Optional<ProdImage> findById(long id) {
        Optional<ImageSqlSchema> schema = imagePersistence.findById(id);
        if(schema.isEmpty()) return Optional.empty();
        ProdImage image = imageMapper.schemaToImage(schema.get());
        return Optional.of(image);
    }
}
