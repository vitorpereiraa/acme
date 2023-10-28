package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.ProdImage;
import com.isep.acme.services.iRepositories.ImageRepository;

@Profile("mongo")
@Component
public class ImageMongoRepository implements ImageRepository {

    @Override
    public Iterable<ProdImage> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Optional<ProdImage> findById(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }
    
}
