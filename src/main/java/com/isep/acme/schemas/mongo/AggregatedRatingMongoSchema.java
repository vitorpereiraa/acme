package com.isep.acme.schemas.mongo;

import javax.persistence.Id;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("mongo")
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedRatingMongoSchema  {
    @Id
    private Long aggregatedId;

    @Field
    private double average;

    @DocumentReference(lazy=true)
    private ProductMongoSchema product;
}
