package com.isep.acme.repositories.sql;

import com.isep.acme.dataModels.sql.ProdImageDataModel;
import com.isep.acme.dataModels.sql.ProdImageMapper;
import com.isep.acme.model.ProdImage;
import com.isep.acme.persistence.sql.ImagePersistence;
import com.isep.acme.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImageRepositoryImpl implements ImageRepository {

    @Autowired
    private ImagePersistence imagePersistence;

    @Override
    public Optional<ProdImage> findById(Long id) {
        Optional<ProdImageDataModel> imageDataModel = imagePersistence.findById(id);
        return imageDataModel.map(ProdImageMapper::DataModelToDomain);
    }

    @Override
    public Iterable<ProdImage> findAll() {
        Iterable<ProdImageDataModel> dataModelIterable = imagePersistence.findAll();
        return ProdImageMapper.DataModelListToDomainList(dataModelIterable);
    }
}
