package com.isep.acme.repositories.neo4j;

import com.isep.acme.model.Product;
import com.isep.acme.persistence.neo4j.ProductNeo4jPersistence;
import com.isep.acme.schemas.neo4j.ProductNeo4jMapper;
import com.isep.acme.services.iRepositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductNeo4jRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductNeo4jPersistence productPersistence;

    @Autowired
    private ProductNeo4jMapper productMapper;

    @Override
    public Iterable<Product> findByDesignation(String designation) {
        var productSchemas = productPersistence.findByDesignation(designation);
        return productMapper.schemaListToDomainList(productSchemas);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        var product = productPersistence.findBySku(sku);
        return product.map(productMapper::schemaToDomain);
    }

    @Override
    public void deleteBySku(String sku) {
        productPersistence.deleteBySku(sku);
    }

    @Override
    public Product updateBySku(String sku) {
        var productSchema = productPersistence.updateBySku(sku);
        return productMapper.schemaToDomain(productSchema);
    }

    @Override
    public Optional<Product> findById(Long productID) {
        var productSchema = productPersistence.findById(productID);
        return productSchema.map(productMapper::schemaToDomain);
    }

    @Override
    public Iterable<Product> findAll() {
        var productSchemas = productPersistence.findAll();
        return productMapper.schemaListToDomainList(productSchemas);
    }

    @Override
    public Product save(Product product) {
        var productSchema = productMapper.domainToSchema(product);
        var savedProductSchema = productPersistence.save(productSchema);
        return productMapper.schemaToDomain(savedProductSchema);
    }
}
