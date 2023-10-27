package com.isep.acme.dataModels.sql;


import com.isep.acme.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User DataModelToDomain(UserDataModel dataModel) {
        return new User(dataModel.getUserId(), dataModel.getUsername(), dataModel.getPassword(), dataModel.getFullName(),
                dataModel.getAuthorities(), dataModel.getNif(), dataModel.getMorada());
    }

    public static List<User> DataModelListToDomainList(Iterable<UserDataModel> dataModelList) {
        List<User> list = new ArrayList<>();
        for (UserDataModel dataModel : dataModelList) {
            User p = DataModelToDomain(dataModel);
            list.add(p);
        }
        return list;
    }

    public static List<UserDataModel> DomainListToDataModelList(Iterable<User> domainList) {
        List<UserDataModel> list = new ArrayList<>();
        for (User domain : domainList) {
            UserDataModel p = DomainToDataModel(domain);
            list.add(p);
        }
        return list;
    }

    public static UserDataModel DomainToDataModel(User domain) {
        return new UserDataModel(domain.getUserId(), domain.getUsername(), domain.getPassword(), domain.getFullName(),
                domain.getAuthorities(), domain.getNif(), domain.getMorada());
    }
}
