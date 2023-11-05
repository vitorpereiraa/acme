package com.isep.acme.schemas.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;


@Node("AggregatedRating")
@AllArgsConstructor
@Getter
public class AggregatedRatingNeo4jjSchema {
    @Id
    @GeneratedValue
    private Long aggregatedId;

    private double average;

    @Relationship(direction = Relationship.Direction.OUTGOING, type = "has")
    private ProductNeo4jSchema product;

    public AggregatedRatingNeo4jjSchema(double average, ProductNeo4jSchema product) {
        this.average = average;
        this.product = product;
    }
}
