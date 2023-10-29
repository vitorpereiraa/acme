package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.ProdImage;
import com.isep.acme.persistence.neo4j.ImageNeo4jPersistence;
import com.isep.acme.schemas.neo4j.ImageNeo4jMapper;
import com.isep.acme.services.iRepositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImageNeo4jRepositoryImpl implements ImageRepository {

    @Autowired
    private ImageNeo4jPersistence persistence;

    @Autowired
    private ImageNeo4jMapper imageMapper;

    @Override
    public Iterable<ProdImage> findAll() {
        var schemaList = persistence.findAll();
        return imageMapper.schemaListToDomainList(schemaList);
    }

    @Override
    public Optional<ProdImage> findById(long id) {
        var schema = persistence.findById(id);
        return schema.map(imageMapper::schemaToDomain);
    }
}
