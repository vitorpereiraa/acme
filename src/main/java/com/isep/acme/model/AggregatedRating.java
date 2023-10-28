package com.isep.acme.model;

public class AggregatedRating {

    private Long aggregatedId;
    private double average;
    private Product product;

    //Constructor used when Creating a AggregatedRating
    public AggregatedRating(double average, Product product) {
        this.average = average;
        this.product = product;
    }

    //Constructor used when converting from schema
    public AggregatedRating(Long aggregatedId, double average, Product product) {
        this.aggregatedId = aggregatedId;
        this.average = average;
        this.product = product;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAggregatedId() {
        return aggregatedId;
    }
}
