package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.model.Refinery;
import org.example.repositories.entities.RefineryEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-06T19:26:08+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class RefineryMapperImpl implements RefineryMapper {

    @Override
    public RefineryEntity toEntity(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryEntity refineryEntity = new RefineryEntity();

        refineryEntity.setId( refinery.getId() );
        refineryEntity.setName( refinery.getName() );
        refineryEntity.setCreatedAt( refinery.getCreatedAt() );

        return refineryEntity;
    }

    @Override
    public Refinery toDomain(RefineryEntity refineryEntity) {
        if ( refineryEntity == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setId( refineryEntity.getId() );
        refinery.setName( refineryEntity.getName() );
        refinery.setCreatedAt( refineryEntity.getCreatedAt() );

        return refinery;
    }
}
