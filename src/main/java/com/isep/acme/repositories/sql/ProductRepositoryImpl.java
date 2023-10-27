package com.isep.acme.repositories.sql;

import com.isep.acme.dataModels.sql.ProductDataModel;
import com.isep.acme.dataModels.sql.ProductMapper;
import com.isep.acme.model.Product;
import com.isep.acme.persistence.sql.ProductPersistence;
import com.isep.acme.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private ProductPersistence productRepository;

    @Override
    public List<Product> findByDesignation(String designation) {
        List<ProductDataModel> productDataModelList = productRepository.findByDesignation(designation);
        return ProductMapper.DataModelListToDomainList(productDataModelList);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        Optional<ProductDataModel> productDataModel = productRepository.findBySku(sku);
        return productDataModel.map(ProductMapper::DataModelToDomain);

    }

    @Override
    public Optional<Product> getCatalog() {
        Optional<ProductDataModel> productDataModelList = productRepository.getCatalog();
        return productDataModelList.map(ProductMapper::DataModelToDomain);
    }

    @Override
    public void deleteBySku(String sku) {
        productRepository.deleteBySku(sku);
    }

    @Override
    public Product updateBySku(String sku) {
        ProductDataModel productDataModel = productRepository.updateBySku(sku);
        return ProductMapper.DataModelToDomain(productDataModel);
    }

    @Override
    public Optional<Product> findById(Long productID) {
        Optional<ProductDataModel> productDataModel = productRepository.findById(productID);
        return Optional.of(ProductMapper.DataModelToDomain(productDataModel.get()));
    }

    @Override
    public Product save(Product product) {
        ProductDataModel dataModel = ProductMapper.DomainToDataModel(product);
        ProductDataModel productDataModel = productRepository.save(dataModel);
        return ProductMapper.DataModelToDomain(productDataModel);
    }

    @Override
    public Iterable<Product> findAll() {
        Iterable<ProductDataModel> dataModelIterable = productRepository.findAll();
        return ProductMapper.DataModelListToDomainList(dataModelIterable);
    }
}
