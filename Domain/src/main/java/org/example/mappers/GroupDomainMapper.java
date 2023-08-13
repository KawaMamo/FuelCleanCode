package org.example.mappers;

import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.request.update.UpdateGroupRequest;
import org.example.contract.response.GroupResponse;
import org.example.model.Group;
import org.mapstruct.Mapper;

@Mapper
public interface GroupDomainMapper {
    Group requestToDomain(CreateGroupRequest request);
    GroupResponse domainToResponse(Group group);
    Group requestToDomain(UpdateGroupRequest request);
}
