package com.isep.acme.dataModels.sql;


import com.isep.acme.model.AggregatedRating;

import java.util.ArrayList;
import java.util.List;

public class AggregatedRatingMapper {
    public static AggregatedRating DataModelToDomain(AggregatedRatingDataModel dataModel) {
        return new AggregatedRating(dataModel.getAggregatedId(), dataModel.getAggregatedId(),
                ProductMapper.DataModelToDomain(dataModel.getProduct()));
    }

    public static List<AggregatedRating> DataModelListToDomainList(Iterable<AggregatedRatingDataModel> dataModelList) {
        List<AggregatedRating> list = new ArrayList<>();
        for (AggregatedRatingDataModel dataModel : dataModelList) {
            AggregatedRating p = DataModelToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public static List<AggregatedRatingDataModel> DomainListToDataModelList(Iterable<AggregatedRating> domainList) {
        List<AggregatedRatingDataModel> list = new ArrayList<>();
        for (AggregatedRating domain : domainList) {
            AggregatedRatingDataModel p = DomainToDataModel(domain);
            list.add(p);
        }
        return list;
    }

    public static AggregatedRatingDataModel DomainToDataModel(AggregatedRating domain) {
        //if (domain.getAggregatedId() != null)
            return new AggregatedRatingDataModel(domain.getAggregatedId(), domain.getAverage(),
                    ProductMapper.DomainToDataModel(domain.getProduct()));

        //return new AggregatedRatingDataModel(domain.getAggregatedId(),
         //       ProductMapper.DomainToDataModel(domain.getProduct()));
    }
}
