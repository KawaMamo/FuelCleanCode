package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateGroupRequest;
import org.example.contract.request.update.UpdateGroupRequest;
import org.example.contract.response.GroupResponse;
import org.example.model.Group;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-03T20:41:19+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class GroupDomainMapperImpl implements GroupDomainMapper {

    @Override
    public Group requestToDomain(CreateGroupRequest request) {
        if ( request == null ) {
            return null;
        }

        Group group = new Group();

        group.setName( request.getName() );

        return group;
    }

    @Override
    public GroupResponse domainToResponse(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupResponse groupResponse = new GroupResponse();

        groupResponse.setId( group.getId() );
        groupResponse.setName( group.getName() );
        groupResponse.setCreatedAt( group.getCreatedAt() );
        groupResponse.setUpdatedAt( group.getUpdatedAt() );

        return groupResponse;
    }

    @Override
    public Group requestToDomain(UpdateGroupRequest request) {
        if ( request == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( request.getId() );
        group.setName( request.getName() );

        return group;
    }
}
