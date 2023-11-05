package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.Vote;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class VoteNeo4jMapper {
    public Vote schemaToDomain(VoteNeo4jSchema schema){
        return new Vote(schema.getVote(), schema.getUserID());
    }

    public VoteNeo4jSchema domainToSchema(Vote vote){
        return new VoteNeo4jSchema(vote.getVote(), vote.getUserID());
    }

    public List<Vote> schemaListToDomainList(Iterable<VoteNeo4jSchema> schemas){
        return StreamSupport
                .stream(schemas.spliterator(), false)
                .map(this::schemaToDomain)
                .collect(Collectors.toList());
    }

    public List<VoteNeo4jSchema> domainListToSchemaList(Iterable<Vote> domains){
        return StreamSupport
                .stream(domains.spliterator(), false)
                .map(this::domainToSchema)
                .collect(Collectors.toList());
    }
}
