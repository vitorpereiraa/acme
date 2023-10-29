package com.isep.acme.persistence.neo4j;

import com.isep.acme.schemas.neo4j.ProductNeo4jSchema;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductNeo4jPersistence extends Neo4jRepository<ProductNeo4jSchema,Long> {

    @Query("MATCH(p:Product) WHERE p.designation=$designation RETURN p")
    List<ProductNeo4jSchema> findByDesignation(String designation);

    @Query("MATCH(p:Product) WHERE p.sku=$sku RETURN p")
    Optional<ProductNeo4jSchema> findBySku(String sku);

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
    //@Query("SELECT p FROM Product p WHERE p.sku=:sku AND p.description=:description")
    @Query("MATCH(p:Product) Return p")
    Optional<ProductNeo4jSchema> getCatalog();

    //Obtain the details of products -> Destails: show sku, designation and description of all products

    //Delete the product when given the SKU
    @Transactional
    @Modifying
    //@Query("DELETE FROM Product p WHERE p.sku=:sku")
    @Query("MATCH(p:Product {sku:$sku}) DELETE p")
    void deleteBySku(String sku);

    //Update the product when given the SKU
    @Transactional
    @Modifying
    //@Query("UPDATE Product p SET p.sku = :sku WHERE p.sku=:sku")
    @Query("MATCH(p:Product {sku: $sku}) SET p.sku = $sku RETURN p")
    ProductNeo4jSchema updateBySku(String sku);

    //@Query("SELECT p FROM Product p WHERE p.productID=:productID")
    //Optional<ProductDataModel> findById(Long productID);
}
