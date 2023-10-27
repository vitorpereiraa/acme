package com.isep.acme.dataModels;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Resource;
import javax.persistence.*;

@Entity(name = "ProdImage")
@AllArgsConstructor
@Getter
public class ProdImageDataModel {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id")
    private ProductDataModel product;

    @Lob
    private Resource image;

    public ProdImageDataModel() {
    }
}
