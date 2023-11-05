package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.Rating;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RatingNeo4jMapper {
    public Rating schemaToDomain(RatingNeo4jSchema dataModel) {
        return new Rating(dataModel.getIdRating(), dataModel.getVersion(), dataModel.getRate());
    }

    public List<Rating> schemaListToDomainList(Iterable<RatingNeo4jSchema> dataModelList) {
        List<Rating> list = new ArrayList<>();
        for (RatingNeo4jSchema dataModel : dataModelList) {
            Rating p = schemaToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public List<RatingNeo4jSchema> DomainListToSchemaList(Iterable<Rating> domainList) {
        List<RatingNeo4jSchema> list = new ArrayList<>();
        for (Rating domain : domainList) {
            RatingNeo4jSchema p = domainToSchema(domain);
            list.add(p);
        }
        return list;
    }

    public RatingNeo4jSchema domainToSchema(Rating domain) {
        return new RatingNeo4jSchema(domain.getIdRating(), domain.getVersion(), domain.getRate());
    }
}
