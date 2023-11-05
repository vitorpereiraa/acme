package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.AggregatedRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AggregatedRatingNeo4jMapper {

    @Autowired
    private ProductNeo4jMapper productMapper;

    public AggregatedRating schemaToDomain(AggregatedRatingNeo4jjSchema dataModel) {
        return new AggregatedRating(dataModel.getAggregatedId(), dataModel.getAggregatedId(),
                productMapper.schemaToDomain(dataModel.getProduct()));
    }

    public List<AggregatedRating> schemaListToDomainList(Iterable<AggregatedRatingNeo4jjSchema> dataModelList) {
        List<AggregatedRating> list = new ArrayList<>();
        for (AggregatedRatingNeo4jjSchema dataModel : dataModelList) {
            AggregatedRating p = schemaToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public List<AggregatedRatingNeo4jjSchema> domainListToSchemaList(Iterable<AggregatedRating> domainList) {
        List<AggregatedRatingNeo4jjSchema> list = new ArrayList<>();
        for (AggregatedRating domain : domainList) {
            AggregatedRatingNeo4jjSchema p = domainToSchema(domain);
            list.add(p);
        }
        return list;
    }

    public AggregatedRatingNeo4jjSchema domainToSchema(AggregatedRating domain) {
        if (domain.getAggregatedId() != null)
            return new AggregatedRatingNeo4jjSchema(domain.getAggregatedId(), domain.getAverage(),
                    productMapper.domainToSchema(domain.getProduct()));

        return new AggregatedRatingNeo4jjSchema(domain.getAverage(),
                productMapper.domainToSchema(domain.getProduct()));
    }
}
