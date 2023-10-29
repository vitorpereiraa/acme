package com.isep.acme.services;

import com.isep.acme.dtos.CreateProductDTO;
import com.isep.acme.dtos.ProductDTO;
import com.isep.acme.dtos.ProductDetailDTO;
import com.isep.acme.dtos.ProductMapper;
import com.isep.acme.model.Product;
import com.isep.acme.services.iRepositories.ProductRepository;
import com.isep.acme.services.iServices.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    @Qualifier("skuGenerator")
    private SkuGenerator skuGenerator;

    @Autowired
    private ProductMapper mapper;

    @Override
    public Optional<ProductDetailDTO> getProductBySku( final String sku ) {
        Optional<Product> product = repository.findBySku(sku);
        if(product.isEmpty()) return Optional.empty();
        ProductDetailDTO dto = mapper.productToDetailedDTO(product.get());
        return Optional.of(dto);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);
        if(product.isEmpty()) return Optional.empty();
        ProductDTO dto = mapper.productToDTO(product.get());
        return Optional.of(dto);
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        return mapper.productsToDTOs(p);
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        return mapper.productsToDTOs(p);
    }

    public ProductDetailDTO getDetails(String sku) {
        Optional<Product> product = repository.findBySku(sku);
        if(product.isEmpty()) return null;
        return mapper.productToDetailedDTO(product.get());
    }


    @Override
    public ProductDTO create(final CreateProductDTO product) {
        String sku = product.getSku().isEmpty()
                ? skuGenerator.generateSku()
                : product.getSku();
        product.setSku(sku);

        final Product p = mapper.creationDTOToProduct(product);
        Product persisted = repository.save(p);
        return mapper.productToDTO(persisted);
    }

    @Override
    public ProductDTO updateBySku(String sku, ProductDetailDTO product) {

        final Optional<Product> productToUpdate = repository.findBySku(sku);

        if( productToUpdate.isEmpty() ) return null;

        productToUpdate.get().updateProduct(product.getDesignation(), product.getDescription());

        Product productUpdated = repository.save(productToUpdate.get());
        
        return mapper.productToDTO(productUpdated);
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }
}
