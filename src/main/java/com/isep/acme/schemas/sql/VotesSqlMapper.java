package com.isep.acme.schemas.sql;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.Vote;

@Profile("sql")
@Component
public class VotesSqlMapper {

    public VoteSqlSchema voteToSchema(Vote vote){
        return new VoteSqlSchema(vote.getVote(),vote.getUserID());
    }

    public List<VoteSqlSchema> votesToSchemas(List<Vote> votes){
        return votes
                .stream() 
                .map(this::voteToSchema)
                .collect(Collectors.toList());
    }

    public Vote schemaToVote(VoteSqlSchema schema) {
        return new Vote(schema.getVote(), schema.getUserID());
    }

    public List<Vote> schemasToVotes(List<VoteSqlSchema> schemas) {
        return schemas
            .stream()
            .map(this::schemaToVote)
            .collect(Collectors.toList());
    }
    
}
