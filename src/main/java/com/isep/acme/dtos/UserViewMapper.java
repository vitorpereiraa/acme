package com.isep.acme.dtos;

import org.mapstruct.Mapper;

import com.isep.acme.model.User;

@Mapper(componentModel = "spring")
public abstract class UserViewMapper {

    public abstract UserView toUserView(User user);
}
