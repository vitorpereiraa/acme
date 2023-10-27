package com.isep.acme.dataModels.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "AggregatedRating")
@AllArgsConstructor
@Getter
public class AggregatedRatingDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggregatedId;

    @Column()
    private double average;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private ProductDataModel product;

    public AggregatedRatingDataModel() {

    }

    public AggregatedRatingDataModel(double average, ProductDataModel productDataModel) {
        this.average=average;
        this.product=productDataModel;
    }
}
