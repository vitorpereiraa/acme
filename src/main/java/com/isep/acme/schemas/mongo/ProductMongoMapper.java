package com.isep.acme.schemas.mongo;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;

@Profile("mongo")
@Component
public class ProductMongoMapper {
    
    public ProductMongoSchema productToSchema(final Product product){
        return new ProductMongoSchema(product.getProductID(),product.getSku(), product.getDesignation(), product.getDescription());
    }

    public Product schemaToProduct(final ProductMongoSchema productSchema){
        return new Product(productSchema.getProductID(),productSchema.getSku(), productSchema.getDesignation(), productSchema.getDescription());
    }

    public Iterable<Product> schemasToProducts(final Iterable<ProductMongoSchema> productSchemas){
        return StreamSupport
                .stream(productSchemas.spliterator(),false)
                .map(this::schemaToProduct)
                .collect(Collectors.toList());
    }
}
