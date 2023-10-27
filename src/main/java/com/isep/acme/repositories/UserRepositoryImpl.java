package com.isep.acme.repositories;

import com.isep.acme.dataModels.ProductMapper;
import com.isep.acme.dataModels.UserDataModel;
import com.isep.acme.dataModels.UserMapper;
import com.isep.acme.model.User;
import com.isep.acme.persistence.UserPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserPersistence userPersistence;

    @Override
    public User save(User user) {
        UserDataModel dataModel = UserMapper.DomainToDataModel(user);
        UserDataModel userDataModel = userPersistence.save(dataModel);
        return UserMapper.DataModelToDomain(userDataModel);
    }

    @Override
    public Optional<User> findById(Long userId) {
        Optional<UserDataModel> userDataModel = userPersistence.findById(userId);
        return userDataModel.map(UserMapper::DataModelToDomain);
    }

    @Override
    public User getById(Long userId) {
        UserDataModel userDataModel = userPersistence.getById(userId);
        return UserMapper.DataModelToDomain(userDataModel);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserDataModel> userDataModel = userPersistence.findByUsername(username);
        return userDataModel.map(UserMapper::DataModelToDomain);
    }
}
