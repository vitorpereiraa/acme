package com.isep.acme.repositories;

import com.isep.acme.model.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findByDesignation(String designation);

    Optional<Product> findBySku(String sku);

    //Obtain the details of products -> Destails: show sku, designation and description of all products
    Optional<Product> getCatalog();

    //Delete the product when given the SKU
    void deleteBySku(String sku);

    //Update the product when given the SKU
    Product updateBySku(String sku);

    Optional<Product> findById(Long productID);

    Product save(Product product);

    Iterable<Product> findAll();
}
