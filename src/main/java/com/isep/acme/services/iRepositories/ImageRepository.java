package com.isep.acme.services.iRepositories;

import java.util.Optional;

import com.isep.acme.model.ProdImage;

public interface ImageRepository {

    Iterable<ProdImage> findAll();

    Optional<ProdImage> findById(final long id);
}
