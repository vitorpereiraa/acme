package com.isep.acme.schemas.sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;

@Profile("sql")
@Component
public class ProductSqlMapper {
    
    public ProductSqlSchema productToSchema(Product product) {
        return new ProductSqlSchema(product.getProductID(),product.getSku(), product.getDesignation(), product.getDescription());
    }

    public Product schemaToProduct (ProductSqlSchema schema) {
        return new Product(schema.getProductID(), schema.getSku(), schema.getDesignation(), schema.getDescription());
    }

    public List<Product> schemasToProducts (Iterable<ProductSqlSchema> schemas) {
        return StreamSupport
                .stream(schemas.spliterator(), false)
                .map(this::schemaToProduct)
                .collect(Collectors.toList());
    }
}
