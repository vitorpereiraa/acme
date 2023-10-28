package com.isep.acme.schemas.sql;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import org.springframework.context.annotation.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Profile("sql")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSqlSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;

    @Version
    private long version;

    @Column(nullable = false)
    private String approvalStatus;

    @Column(nullable = false)
    private String reviewText;

    @ElementCollection
    @Column(nullable = true)
    private List<VoteSqlSchema> upVote;

    @ElementCollection
    @Column(nullable = true)
    private List<VoteSqlSchema> downVote;

    @Column(nullable = true)
    private String report;

    @Column(nullable = false)
    private LocalDate publishingDate;

    @Column(nullable = false)
    private String funFact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductSqlSchema product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserSqlSchema user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private RatingSqlSchema rating;
}
