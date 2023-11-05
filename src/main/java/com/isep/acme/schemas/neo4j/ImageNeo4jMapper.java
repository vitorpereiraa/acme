package com.isep.acme.schemas.neo4j;

import com.isep.acme.model.ProdImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ImageNeo4jMapper {

    @Autowired
    private ProductNeo4jMapper productMapper;

    public ProdImage schemaToDomain(ImageNeo4jSchema schema){
        var productSchema = productMapper.schemaToDomain(schema.getProduct());
        return new ProdImage(schema.getId(), productSchema, schema.getImage());
    }

    public List<ProdImage> schemaListToDomainList(Iterable<ImageNeo4jSchema> schemas){
        return StreamSupport
                .stream(schemas.spliterator(), false)
                .map(this::schemaToDomain)
                .collect(Collectors.toList());
    }

}
