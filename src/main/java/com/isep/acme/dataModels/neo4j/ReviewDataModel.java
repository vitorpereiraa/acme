package com.isep.acme.dataModels.neo4j;

import com.isep.acme.model.Product;
import com.isep.acme.model.Rating;
import com.isep.acme.model.User;
import com.isep.acme.model.Vote;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Version;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDate;
import java.util.List;


@Node("Review")
@Getter
@AllArgsConstructor
public class ReviewDataModel {
    @Id
    @GeneratedValue
    private Long idReview;

    @Version
    private long version;

    private String approvalStatus;

    private String reviewText;

    private List<Vote> upVote;

    @Relationship(direction = Relationship.Direction.OUTGOING, type = "has")
    private List<Vote> downVote;

    private String report;

    private LocalDate publishingDate;

    private String funFact;

    @Relationship(direction = Relationship.Direction.INCOMING, type = "belongs_to")
    private Product product;

    @Relationship(direction = Relationship.Direction.OUTGOING, type = "has")
    private Rating rating;

    @Relationship(direction = Relationship.Direction.INCOMING, type = "is_made_by")
    private User user;
}
