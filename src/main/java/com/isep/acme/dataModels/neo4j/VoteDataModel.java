package com.isep.acme.dataModels.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Vote")
@Getter
@AllArgsConstructor
public class VoteDataModel {
    private String vote;
    private Long userID;
}
