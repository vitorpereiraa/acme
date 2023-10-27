package com.isep.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AggregatedRating {

    private Long aggregatedId;

    private double average;

    private Product product;

    protected AggregatedRating() {}

    public AggregatedRating(double average, Product product) {
        this.average = average;
        this.product = product;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
