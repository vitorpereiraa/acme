package com.isep.acme.schemas.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Node;

@Getter
@AllArgsConstructor
public class VoteNeo4jSchema {
    private String vote;
    private Long userID;
}
