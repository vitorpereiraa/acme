package com.isep.acme.dataModels.sql;

import com.isep.acme.model.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingMapper {
    public static Rating DataModelToDomain(RatingDataModel dataModel) {
        return new Rating(dataModel.getIdRating(), dataModel.getVersion(), dataModel.getRate());
    }

    public static List<Rating> DataModelListToDomainList(Iterable<RatingDataModel> dataModelList) {
        List<Rating> list = new ArrayList<>();
        for (RatingDataModel dataModel : dataModelList) {
            Rating p = DataModelToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public static List<RatingDataModel> DomainListToDataModelList(Iterable<Rating> domainList) {
        List<RatingDataModel> list = new ArrayList<>();
        for (Rating domain : domainList) {
            RatingDataModel p = DomainToDataModel(domain);
            list.add(p);
        }
        return list;
    }

    public static RatingDataModel DomainToDataModel(Rating domain) {
        return new RatingDataModel(domain.getIdRating(), domain.getVersion(), domain.getRate());
    }
}
