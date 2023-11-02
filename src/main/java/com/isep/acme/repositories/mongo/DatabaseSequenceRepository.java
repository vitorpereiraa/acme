package com.isep.acme.repositories.mongo;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.isep.acme.schemas.mongo.DatabaseSequenceMongoSchema;

@Profile("mongo")
@Component
public class DatabaseSequenceRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        DatabaseSequenceMongoSchema counter = mongoOperations.findAndModify(
            query(where("_id").is(seqName)),
            new Update().inc("seq",1), options().returnNew(true).upsert(true),
            DatabaseSequenceMongoSchema.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}