package com.isep.acme.dtos;

import lombok.Getter;

@Getter
public class CreateProductDto {
    private String sku;
    private String designation;
    private String description;

    public CreateProductDto(String sku, String designation, String description) {
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }
    
    public CreateProductDto(){}

    public void setSku(String sku) {
        this.sku = sku;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
