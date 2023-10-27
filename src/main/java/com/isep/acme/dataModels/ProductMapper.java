package com.isep.acme.dataModels;

import com.isep.acme.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    public static Product DataModelToDomain(ProductDataModel productDataModel) {
        return new Product(productDataModel.getProductID(), productDataModel.getSku(), productDataModel.getDesignation(), productDataModel.getDescription());
    }

    public static List<Product> DataModelListToDomainList(Iterable<ProductDataModel> productDataModelList) {
        List<Product> productList = new ArrayList<>();
        for (ProductDataModel dataModel : productDataModelList) {
            Product p = DataModelToDomain(dataModel);
            productList.add(p);
        }
        return productList;
    }

    public static ProductDataModel DomainToDataModel(Product product) {
        return new ProductDataModel(product.getProductID(), product.getSku(), product.getDesignation(), product.getDescription());
    }
}
