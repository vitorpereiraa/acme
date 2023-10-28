package com.isep.acme.schemas.mongo;

import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.context.annotation.Profile;
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

    @Id
    private Long idRating;

    @Version
    private long version;

    @Field
    private Double rate;
}
