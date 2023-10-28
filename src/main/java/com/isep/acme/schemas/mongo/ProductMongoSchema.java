package com.isep.acme.schemas.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("mongo")
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductMongoSchema {

    @Transient
    public static final String SEQUENCE_NAME = "products_sequence";
    
    @Id
    private Long productID;

    @Indexed(unique = true)
    private String sku;

    @Field
    private String designation;

    @Field
    private String description;
}
