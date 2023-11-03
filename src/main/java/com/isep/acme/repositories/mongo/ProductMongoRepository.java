package com.isep.acme.repositories.mongo;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;
import com.isep.acme.persistence.mongo.ProductMongoPersistence;
import com.isep.acme.schemas.mongo.ProductMongoMapper;
import com.isep.acme.schemas.mongo.ProductMongoSchema;
import com.isep.acme.services.iRepositories.ProductRepository;

@Profile("mongo")
@Component
public class ProductMongoRepository implements ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductMongoRepository.class);

    @Autowired
    ProductMongoPersistence productPersistence;

    @Autowired
    DatabaseSequenceRepository sequenceGenerator;

    @Autowired
    ProductMongoMapper productMapper;

    @Override
    public Iterable<Product> findByDesignation(String designation) {
        List<ProductMongoSchema> schemas = productPersistence.findByDesignation(designation);
        return productMapper.schemasToProducts(schemas);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        Optional<ProductMongoSchema> schema = productPersistence.findBySku(sku);
        if(schema.isEmpty()) return Optional.empty();
        logger.info("FIND BY SKU:" + schema.toString());
        Product p = productMapper.schemaToProduct(schema.get());
        return Optional.of(p);
    }

    @Override
    public void deleteBySku(String sku) {
        productPersistence.deleteBySku(sku);
    }

    @Override
    public Optional<Product> findById(Long productID) {
        Optional<ProductMongoSchema> schema = productPersistence.findById(productID);
        if(schema.isEmpty()) return Optional.empty();
        Product p = productMapper.schemaToProduct(schema.get());
        return Optional.of(p);
    }

    @Override
    public Iterable<Product> findAll() {
        Iterable<ProductMongoSchema> schemas = productPersistence.findAll();
        return productMapper.schemasToProducts(schemas);
    }

    @Override
    public Product save(Product product) {
        ProductMongoSchema schema = productMapper.productToSchema(product);
        if(schema.getProductID() == null){
            schema.setProductID(sequenceGenerator.generateSequence(ProductMongoSchema.SEQUENCE_NAME));
        }
        logger.info("SAVING: " + schema.toString());
        ProductMongoSchema persisted = productPersistence.save(schema);
        return productMapper.schemaToProduct(persisted);
    }

    @Override
    public Product updateBySku(String sku) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBySku'");
    }
}
