package com.isep.acme.dtos;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

import com.isep.acme.model.Product;

@Component
public class ProductMapper {
    
    public ProductDetailDTO productToDetailedDTO(Product product){
        return new ProductDetailDTO(product.getSku(), product.getDesignation(), product.getDescription());
    }

    public ProductDTO productToDTO(Product product){
        return new ProductDTO(product.getSku(), product.getDesignation());
    } 

    public Iterable<ProductDTO> productsToDTOs(Iterable<Product> product){
        return StreamSupport
            .stream(product.spliterator(), false)
            .map(this::productToDTO)
            .collect(Collectors.toList());
    } 

    public Product detailedDTOToProduct(ProductDetailDTO product){
        return new Product(product.getSku(), product.getDesignation(), product.getDescription());
    }
}

