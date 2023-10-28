package com.isep.acme.schemas.mongo;

import javax.persistence.*;

import org.springframework.context.annotation.Profile;
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
    
    @Id
    public Long productID;

    @Indexed(unique = true)
    public String sku;

    @Field
    public String designation;

    @Field
    public String description;
}
