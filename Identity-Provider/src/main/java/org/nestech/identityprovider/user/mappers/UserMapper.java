package org.nestech.identityprovider.user.mappers;

import org.mapstruct.Mapper;
import org.nestech.identityprovider.user.model.User;
import org.nestech.identityprovider.user.responses.UserResponse;

@Mapper
public interface UserMapper {
    UserResponse domainToResponse(User user);
}
