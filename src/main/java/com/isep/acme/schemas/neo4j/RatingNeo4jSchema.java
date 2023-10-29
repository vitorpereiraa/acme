package com.isep.acme.schemas.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


@Node("Rating")
@Getter
@AllArgsConstructor
public class RatingNeo4jSchema {
    @Id
    @GeneratedValue
    private Long idRating;

    @Version
    private long version;

    private Double rate;

}
