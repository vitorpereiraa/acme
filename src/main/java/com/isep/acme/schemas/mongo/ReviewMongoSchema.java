package com.isep.acme.schemas.mongo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
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

    @Transient
    public static final String SEQUENCE_NAME = "reviews_sequence";

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

    @DocumentReference(lazy=true)
    private ProductMongoSchema product;

    @DocumentReference(lazy=true)
    private UserMongoSchema user;

    @DocumentReference(lazy=true)
    private RatingMongoSchema rating;
}
