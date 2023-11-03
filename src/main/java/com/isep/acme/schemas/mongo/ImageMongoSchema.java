package com.isep.acme.schemas.mongo;

import javax.annotation.Resource;
import javax.persistence.Lob;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
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

    @Transient
    public static final String SEQUENCE_NAME = "images_sequence";

    @Id
    private Long id;
    
    @DocumentReference(lazy=true)
    private ProductMongoSchema product;

    @Lob
    private Resource image;
}
