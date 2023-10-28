package com.isep.acme.services.iRepositories;

import com.isep.acme.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User entity);
    Optional<User> findById(Long userId);
    User getById(final Long userId);
    Optional<User> findByUsername(String username);
}