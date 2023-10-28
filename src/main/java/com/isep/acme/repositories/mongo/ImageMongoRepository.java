package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.ProdImage;
import com.isep.acme.persistence.mongo.ImageMongoPersistence;
import com.isep.acme.schemas.mongo.ImageMongoMapper;
import com.isep.acme.schemas.mongo.ImageMongoSchema;
import com.isep.acme.services.iRepositories.ImageRepository;

@Profile("mongo")
@Component
public class ImageMongoRepository implements ImageRepository {

    @Autowired
    ImageMongoPersistence imagePersistence;

    @Autowired
    ImageMongoMapper imageMapper;

    @Override
    public Iterable<ProdImage> findAll() {
        Iterable<ImageMongoSchema> schemas = imagePersistence.findAll();
        return imageMapper.schemasToImages(schemas);
    }

    @Override
    public Optional<ProdImage> findById(long id) {
        Optional<ImageMongoSchema> schema = imagePersistence.findById(id);
        if(schema.isEmpty()) return Optional.empty();
        ProdImage image = imageMapper.schemaToImage(schema.get());
        return Optional.of(image);
    }
}
