package com.isep.acme.schemas.sql;

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
public class AggregatedRatingSqlSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggregatedId;

    @Column()
    private double average;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, optional = false)
    private ProductSqlSchema product;
}
