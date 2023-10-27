package com.isep.acme.model;



import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
public class Rating {

    private Long idRating;

    private long version;

    private Double rate;

    protected Rating(){}

    public Rating(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version = Objects.requireNonNull(version);
        setRate(rate);
    }

    public Rating(Double rate) {
        setRate(rate);
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
