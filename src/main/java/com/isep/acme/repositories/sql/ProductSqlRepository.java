package com.isep.acme.repositories.sql;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.persistence.sql.ProductSqlPersistence;
import com.isep.acme.schemas.sql.ProductSqlMapper;
import com.isep.acme.schemas.sql.ProductSqlSchema;
import com.isep.acme.services.iRepositories.ProductRepository;

@Profile("sql")
@Component
public class ProductSqlRepository implements ProductRepository {

    @Autowired
    ProductSqlPersistence productPersistence;

    @Autowired
    ProductSqlMapper productMapper;

    @Override
    public Iterable<Product> findByDesignation(String designation) {
        List<ProductSqlSchema> schemas = productPersistence.findByDesignation(designation);
        return productMapper.schemasToProducts(schemas);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        Optional<ProductSqlSchema> schema = productPersistence.findBySku(sku);
        if(schema.isEmpty()) return Optional.empty();
        Product p = productMapper.schemaToProduct(schema.get());
        return Optional.of(p);
    }

    @Override
    public void deleteBySku(String sku) {
        productPersistence.deleteBySku(sku);
    }

    @Override
    public Product updateBySku(String sku) {
        ProductSqlSchema schema = productPersistence.updateBySku(sku);
        return productMapper.schemaToProduct(schema);
    }

    @Override
    public Optional<Product> findById(Long productID) {
        Optional<ProductSqlSchema> schema = productPersistence.findById(productID);
        if(schema.isEmpty()) return Optional.empty();
        Product p = productMapper.schemaToProduct(schema.get());
        return Optional.of(p);
    }

    @Override
    public Iterable<Product> findAll() {
        Iterable<ProductSqlSchema> schemas = productPersistence.findAll();
        return productMapper.schemasToProducts(schemas);
    }

    @Override
    public Product save(Product product) {
        ProductSqlSchema schema = productMapper.productToSchema(product);
        ProductSqlSchema persisted = productPersistence.save(schema);
        return productMapper.schemaToProduct(persisted);
    }

    // @Override
    // public Product update(Product product) {
    //     ProductSqlSchema schema = productMapper.productToSchema(product);
    //     ProductSqlSchema persisted = productPersistence.save(schema);
    //     return productMapper.schemaToProduct(persisted);
    // }
}
