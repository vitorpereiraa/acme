package com.isep.acme.dtos;

import lombok.Data;

@Data
public class CreateProductDTO {
    private String sku;
    private String designation;
    private String description;

    public CreateProductDTO(String sku, String designation, String description) {
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }

    public CreateProductDTO() {
    }

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
