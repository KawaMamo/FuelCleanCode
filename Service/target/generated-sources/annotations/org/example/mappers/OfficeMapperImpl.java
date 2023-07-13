package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.OfficeEntity;
import org.example.model.Office;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T16:54:43+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class OfficeMapperImpl implements OfficeMapper {

    @Override
    public OfficeEntity domainToEntity(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeEntity officeEntity = new OfficeEntity();

        officeEntity.setId( office.getId() );
        officeEntity.setName( office.getName() );
        officeEntity.setCreatedAt( office.getCreatedAt() );

        return officeEntity;
    }

    @Override
    public Office entityToDomain(OfficeEntity officeEntity) {
        if ( officeEntity == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( officeEntity.getId() );
        office.setName( officeEntity.getName() );
        office.setCreatedAt( officeEntity.getCreatedAt() );

        return office;
    }
}
