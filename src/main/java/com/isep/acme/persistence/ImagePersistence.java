package com.isep.acme.persistence;

import com.isep.acme.dataModels.sql.ProdImageDataModel;
import org.springframework.data.repository.CrudRepository;

public interface ImagePersistence extends CrudRepository<ProdImageDataModel, Long> {
}
