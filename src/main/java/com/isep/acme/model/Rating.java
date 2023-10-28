package com.isep.acme.model;

import java.util.Objects;

public class Rating {

    private Long idRating;
    private long version;
    private Double rate;

    public Rating(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version = Objects.requireNonNull(version);
        setRate(rate);
    }

    public Rating(Double rate) {
        setRate(rate);
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getIdRating(){
        return this.idRating;
    }

    public long getVersion(){
        return this.version;
    }
}
