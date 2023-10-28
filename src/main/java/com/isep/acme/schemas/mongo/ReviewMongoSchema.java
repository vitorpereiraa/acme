package com.isep.acme.schemas.mongo;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("mongo")
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewMongoSchema {

    @Id
    private Long idReview;

    @Version
    private long version;

    @Field
    private String approvalStatus;

    @Field
    private String reviewText;

    @Field
    private List<VoteMongoSchema> upVote;

    @Field
    private List<VoteMongoSchema> downVote;

    @Field
    private String report;

    @Field
    private LocalDate publishingDate;

    @Field
    private String funFact;

    @DocumentReference(lazy=true, lookup="{'ProductMongoSchema':?#{#self._id} }")
    private ProductMongoSchema product;

    @DocumentReference(lazy=true, lookup="{'UserMongoSchema':?#{#self._id} }")
    private UserMongoSchema user;

    @DocumentReference(lazy=true, lookup="{'RatingMongoSchema':?#{#self._id} }")
    private RatingMongoSchema rating;
}
