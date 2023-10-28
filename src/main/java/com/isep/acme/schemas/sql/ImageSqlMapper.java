package com.isep.acme.schemas.sql;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.isep.acme.model.ProdImage;
import com.isep.acme.model.Product;

@Profile("sql")
@Component
public class ImageSqlMapper {

    @Autowired
    ProductSqlMapper productMapper;

    public List<ProdImage> schemasToImages(Iterable<ImageSqlSchema> schemas) {
        return StreamSupport
                .stream(schemas.spliterator(), false)
                .map(this::schemaToImage)
                .collect(Collectors.toList());
    }

    public ProdImage schemaToImage(ImageSqlSchema imageRelationalSchema) {
        Product product = productMapper.schemaToProduct(imageRelationalSchema.getProduct());
        return new ProdImage(imageRelationalSchema.getId(),product, imageRelationalSchema.getImage());
    }
    
}
