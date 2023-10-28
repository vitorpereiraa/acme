package com.isep.acme.persistence.sql;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.isep.acme.schemas.sql.ProductSqlSchema;

import java.util.List;
import java.util.Optional;

@Profile("sql")
@Repository
public interface ProductSqlPersistence extends CrudRepository<ProductSqlSchema, Long> {

    List<ProductSqlSchema> findByDesignation(String designation);

    Optional<ProductSqlSchema> findBySku(String sku);

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
    @Query("SELECT p FROM ProductSqlSchema p WHERE p.sku=:sku AND p.description=:description")
    Optional<ProductSqlSchema> getCatalog();

    //Obtain the details of products -> Destails: show sku, designation and description of all products

    //Delete the product when given the SKU
    @Transactional
    @Modifying
    @Query("DELETE FROM ProductSqlSchema p WHERE p.sku=:sku")
    void deleteBySku(@Param("sku") String sku);

    //Update the product when given the SKU
    @Transactional
    @Modifying
    @Query("UPDATE ProductSqlSchema p SET p.sku = :sku WHERE p.sku=:sku")
    ProductSqlSchema updateBySku(@Param("sku") String sku);

    @Query("SELECT p FROM ProductSqlSchema p WHERE p.productID=:productID")
    Optional<ProductSqlSchema> findById(Long productID);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

}

