package com.isep.acme.schemas.mongo;

import javax.persistence.Id;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("mongo")
@Document(collection = "database_sequences")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseSequenceMongoSchema {

    @Id
    private String id;

    private long seq;
}