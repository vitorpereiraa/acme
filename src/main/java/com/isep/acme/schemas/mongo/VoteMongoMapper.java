package com.isep.acme.schemas.mongo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Vote;

@Profile("mongo")
@Component
public class VoteMongoMapper {

    public VoteMongoSchema voteToSchema(Vote vote){
        return new VoteMongoSchema(vote.getVote(),vote.getUserID());
    }

    public List<VoteMongoSchema> votesToSchemas(List<Vote> votes){
        return votes
                .stream() 
                .map(this::voteToSchema)
                .collect(Collectors.toList());
    }

    public Vote schemaToVote(VoteMongoSchema schema) {
        return new Vote(schema.getVote(), schema.getUserID());
    }

    public List<Vote> schemasToVotes(List<VoteMongoSchema> schemas) {
        return schemas
            .stream()
            .map(this::schemaToVote)
            .collect(Collectors.toList());
    }
    
    
}
