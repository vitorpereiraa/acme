package com.isep.acme.controllers;

import com.isep.acme.dtos.CreateProductDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.dtos.ProductDetailDTO;
import com.isep.acme.services.iServices.ProductService;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Tag(name = "Product", description = "Endpoints for managing  products")
@RestController
@RequestMapping("/products")
class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;


    @Operation(summary = "gets catalog, i.e. all products")
    @GetMapping
    public ResponseEntity<Iterable<ProductDTO>> getCatalog() {
       final var products = service.getCatalog();

       return ResponseEntity.ok().body( products );
    }

    @Operation(summary = "finds product by sku")
    @GetMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> getProductBySku(@PathVariable("sku") final String sku) {

        final Optional<ProductDTO> product = service.findBySku(sku);

        if( product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(product.get());
    }

    @Operation(summary = "finds product by designation")
    @GetMapping(value = "/designation/{designation}")
    public ResponseEntity<Iterable<ProductDTO>> findAllByDesignation(@PathVariable("designation") final String designation){

        final Iterable<ProductDTO> products = service.findByDesignation( designation );
        
        return ResponseEntity.ok().body( products );
    }

    @Operation(summary = "creates a product")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductDTO> create(@RequestBody CreateProductDTO manager) {
        try {
            final ProductDTO product = service.create(manager);
            return new ResponseEntity<ProductDTO>(product, HttpStatus.CREATED);
        }
        catch( Exception e ) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Product must have a unique SKU.");
        }
    }

    @Operation(summary = "updates a product")
    @PatchMapping(value = "/{sku}")
    public ResponseEntity<ProductDTO> Update(@PathVariable("sku") final String sku, @RequestBody final ProductDetailDTO product) {

        final ProductDTO productDTO = service.updateBySku(sku, product);

        if( productDTO == null )
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found.");
        else
            return ResponseEntity.ok().body(productDTO);
    }

    @Operation(summary = "deletes a product")
    @DeleteMapping(value = "/{sku}")
    public ResponseEntity<ProductDetailDTO> delete(@PathVariable("sku") final String sku ){

        service.deleteBySku(sku);
        return ResponseEntity.noContent().build();
    }
}