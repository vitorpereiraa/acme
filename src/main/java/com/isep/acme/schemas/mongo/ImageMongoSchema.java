package com.isep.acme.schemas.mongo;

import javax.annotation.Resource;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("mongo")
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageMongoSchema {

    @Id
    private Long id;
    
    @DocumentReference(lazy=true)
    private ProductMongoSchema product;

    @Lob
    private Resource image;
}
