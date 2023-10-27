package com.isep.acme.dataModels.neo4j;

import com.isep.acme.model.Vote;

public class VoteMapper {
    public static Vote SchemaToDomain(VoteDataModel schema){
        return new Vote(schema.getVote(), schema.getUserID());
    }

    public static VoteDataModel DomainToSchema(Vote vote){
        return new VoteDataModel(vote.getVote(), vote.getUserID());
    }
}
