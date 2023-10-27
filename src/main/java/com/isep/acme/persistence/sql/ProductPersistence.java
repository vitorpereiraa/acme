package com.isep.acme.persistence.sql;

import com.isep.acme.dataModels.sql.ProductDataModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductPersistence extends CrudRepository<ProductDataModel, Long> {
    List<ProductDataModel> findByDesignation(String designation);

    Optional<ProductDataModel> findBySku(String sku);

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
    @Query("SELECT p FROM Product p WHERE p.sku=:sku AND p.description=:description")
    Optional<ProductDataModel> getCatalog();

    //Obtain the details of products -> Destails: show sku, designation and description of all products

    //Delete the product when given the SKU
    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.sku=:sku")
    void deleteBySku(@Param("sku") String sku);

    //Update the product when given the SKU
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.sku = :sku WHERE p.sku=:sku")
    ProductDataModel updateBySku(@Param("sku") String sku);

    @Query("SELECT p FROM Product p WHERE p.productID=:productID")
    Optional<ProductDataModel> findById(Long productID);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

}

