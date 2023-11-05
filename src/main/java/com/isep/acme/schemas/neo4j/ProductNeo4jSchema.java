package com.isep.acme.schemas.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Product")
@Getter
@AllArgsConstructor
public class ProductNeo4jSchema {
    @Id
    @GeneratedValue
    private Long productID;

    private String sku;

    private String designation;

    private String description;
}
