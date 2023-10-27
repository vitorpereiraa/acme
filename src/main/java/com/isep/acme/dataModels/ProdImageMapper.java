package com.isep.acme.dataModels;


import com.isep.acme.model.ProdImage;

import java.util.ArrayList;
import java.util.List;

public class ProdImageMapper {
    public static ProdImage DataModelToDomain(ProdImageDataModel dataModel) {
        return new ProdImage(dataModel.getId(), ProductMapper.DataModelToDomain(dataModel.getProduct()), dataModel.getImage());
    }

    public static List<ProdImage> DataModelListToDomainList(Iterable<ProdImageDataModel> dataModelList) {
        List<ProdImage> list = new ArrayList<>();
        for (ProdImageDataModel dataModel : dataModelList) {
            ProdImage p = DataModelToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public static List<ProdImageDataModel> DomainListToDataModelList(Iterable<ProdImage> domainList) {
        List<ProdImageDataModel> list = new ArrayList<>();
        for (ProdImage domain : domainList) {
            ProdImageDataModel p = DomainToDataModel(domain);
            list.add(p);
        }
        return list;
    }

    public static ProdImageDataModel DomainToDataModel(ProdImage domain) {
        return new ProdImageDataModel(domain.getId(), ProductMapper.DomainToDataModel(domain.getProduct()), domain.getImage());
    }
}
