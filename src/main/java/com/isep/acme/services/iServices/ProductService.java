package com.isep.acme.services.iServices;

import java.util.Optional;

import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.dtos.ProductDetailDTO;

public interface ProductService {

    Optional<ProductDTO> findBySku(final String sku);

    Optional<ProductDetailDTO> getProductBySku( final String sku );

    Iterable<ProductDTO> findByDesignation(final String designation);

    Iterable<ProductDTO> getCatalog();

    ProductDetailDTO getDetails(final String sku);

    ProductDTO create(final ProductDetailDTO manager);

    ProductDTO updateBySku(final String sku, final ProductDetailDTO product);

    void deleteBySku(final String sku);
}
