package com.isep.acme.schemas.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Vote")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoteNeo4jSchema {

    @Id
    @GeneratedValue
    private Long id;

    private String vote;
    private Long userID;

    public VoteNeo4jSchema(String vote, Long userID) {
        this.vote = vote;
        this.userID = userID;
    }
}
