package com.isep.acme.dataModels.neo4j;

import com.isep.acme.model.Product;

public class ProductMapper {
    public static Product SchemaToDomain(ProductDataModel schema){
        return new Product(schema.getProductID(), schema.getSku(), schema.getDesignation(), schema.getDescription());
    }

    public static ProductDataModel DomainToSchema(Product product){
        return new ProductDataModel(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
    }
}
