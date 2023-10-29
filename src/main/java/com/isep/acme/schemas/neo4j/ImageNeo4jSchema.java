package com.isep.acme.schemas.neo4j;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import javax.annotation.Resource;

@Node("Image")
@Getter
@AllArgsConstructor
public class ImageNeo4jSchema {

    @Id
    @GeneratedValue
    private Long id;

    @Relationship(direction = Relationship.Direction.INCOMING, type ="has")
    private ProductNeo4jSchema product;

    //TODO check
    private Resource image;
}
