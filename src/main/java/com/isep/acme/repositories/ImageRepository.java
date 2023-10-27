package com.isep.acme.repositories;

import com.isep.acme.model.ProdImage;

import java.util.Optional;

public interface ImageRepository {
    Optional<ProdImage> findById(Long id);

    Iterable<ProdImage> findAll();
}
