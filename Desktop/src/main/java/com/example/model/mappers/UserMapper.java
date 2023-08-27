package com.example.model.mappers;

import com.example.model.user.User;
import com.example.model.user.response.UserResponseList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper
public interface UserMapper {
    @Mapping(source = "locked", target ="isActive", qualifiedByName = "status")
    User toDomain(UserResponseList userResponseList);

    @Named("status")
    static Integer status(boolean locked){
        return locked? 0 : 1;
    }
}
