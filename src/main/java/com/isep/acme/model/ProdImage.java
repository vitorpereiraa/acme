package com.isep.acme.model;

import com.isep.acme.dtos.ImageDTO;

import javax.annotation.Resource;

import com.isep.acme.dtos.ImageDTO;

public class ProdImage {

    private Long id;
    private Product product;
    private Resource image;

    public ProdImage(Long id, Product product, Resource image){
        this.id = id;
        this.product = product;
        // addImage(image);;
    }

    public ImageDTO toDto() {
        return new ImageDTO(this.id, product.getProductID());
    }

/*
    public ImageService addImage( Resource image){

        return new AddImage (this.image);
    }
*/
}

