package com.isep.acme.services.iRepositories;
import com.isep.acme.model.Product;

import java.util.Optional;

public interface ProductRepository {

    Iterable<Product> findByDesignation(String designation);

    Optional<Product> findBySku(String sku);

    void deleteBySku(String sku);

    Product updateBySku(String sku);

    Optional<Product> findById(Long productID);

    Iterable<Product> findAll();

    Product save(Product product);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */
}

