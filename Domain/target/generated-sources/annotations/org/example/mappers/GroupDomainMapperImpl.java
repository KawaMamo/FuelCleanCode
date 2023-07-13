package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateGroupRequest;
import org.example.contract.response.GroupResponse;
import org.example.model.Group;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T16:54:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
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

        return groupResponse;
    }
}
