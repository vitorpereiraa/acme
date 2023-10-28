package com.isep.acme.repositories.mongo;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.services.iRepositories.ProductRepository;

@Profile("mongo")
@Component
public class ProductMongoRepository implements ProductRepository {

    @Override
    public Iterable<Product> findByDesignation(String designation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDesignation'");
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBySku'");
    }

    @Override
    public void deleteBySku(String sku) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteBySku'");
    }

    @Override
    public Product updateBySku(String sku) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBySku'");
    }

    @Override
    public Optional<Product> findById(Long productID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Iterable<Product> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public Product save(Product product) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
