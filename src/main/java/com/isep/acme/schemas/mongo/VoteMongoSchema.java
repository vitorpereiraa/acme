package com.isep.acme.schemas.mongo;

import org.springframework.context.annotation.Profile;
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
public class VoteMongoSchema {
    @Field
    private String vote;
    @Field
    private Long userID;
}
