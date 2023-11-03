package com.isep.acme.schemas.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("mongo")
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingMongoSchema {

    @Transient
    public static final String SEQUENCE_NAME = "rating_sequence";

    @Id
    private Long idRating;

    @Version
    private long version;

    @Field
    private Double rate;
}
