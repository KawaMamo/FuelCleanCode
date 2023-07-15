package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.GroupEntity;
import org.example.model.Group;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T22:39:41+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupEntity domainToEntity(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupEntity groupEntity = new GroupEntity();

        groupEntity.setId( group.getId() );
        groupEntity.setName( group.getName() );
        groupEntity.setCreatedAt( group.getCreatedAt() );

        return groupEntity;
    }

    @Override
    public Group entityToDomain(GroupEntity group) {
        if ( group == null ) {
            return null;
        }

        Group group1 = new Group();

        group1.setId( group.getId() );
        group1.setName( group.getName() );
        group1.setCreatedAt( group.getCreatedAt() );

        return group1;
    }
}
