package com.isep.acme.persistence;

import com.isep.acme.dataModels.ProdImageDataModel;
import org.springframework.data.repository.CrudRepository;

import com.isep.acme.model.ProdImage;

public interface ImagePersistence extends CrudRepository<ProdImageDataModel, Long> {
}
