package com.isep.acme.dataModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
@Getter
public class VoteDataModel {
    private String vote;
    private Long userID;

    public VoteDataModel() {

    }
}
