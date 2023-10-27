package com.isep.acme.repositories;

import com.isep.acme.dataModels.ProdImageDataModel;
import com.isep.acme.dataModels.ProdImageMapper;
import com.isep.acme.model.ProdImage;
import com.isep.acme.persistence.ImagePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ImageRepositoryImpl implements ImageRepository{

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
