package com.isep.acme.dataModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity(name = "Rating")
@AllArgsConstructor
@Getter
public class RatingDataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRating;

    @Version
    private long version;

    @Column(nullable = false)
    private Double rate;

    public RatingDataModel() {
    }
}
