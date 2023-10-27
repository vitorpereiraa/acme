package com.isep.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.annotation.Resource;
import javax.persistence.*;

@AllArgsConstructor
@Getter
public class ProdImage {

    private Long id;

    private Product product;

    private Resource image;

    public ProdImage(Product product, Resource image) {
        setProduct(product);
        //addImage(image);;
    }

    public ProdImage() {
    }

    private void setProduct(Product product) {
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

