package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class  ProductNeo4jMapper {
    public Product schemaToDomain(ProductNeo4jSchema productNeo4jSchema) {
        return new Product(productNeo4jSchema.getProductID(), productNeo4jSchema.getSku(), productNeo4jSchema.getDesignation(), productNeo4jSchema.getDescription());
    }

    public List<Product> schemaListToDomainList(Iterable<ProductNeo4jSchema> productNeo4jSchemas) {
        List<Product> productList = new ArrayList<>();
        for (ProductNeo4jSchema neo4jSchema : productNeo4jSchemas) {
            Product p = schemaToDomain(neo4jSchema);
            productList.add(p);
        }
        return productList;
    }

    public ProductNeo4jSchema domainToSchema(Product product) {
        return new ProductNeo4jSchema(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
    }
}
