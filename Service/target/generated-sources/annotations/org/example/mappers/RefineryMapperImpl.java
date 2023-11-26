package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.RefineryEntity;
import org.example.model.Refinery;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-25T14:56:30+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class RefineryMapperImpl implements RefineryMapper {

    @Override
    public RefineryEntity toEntity(Refinery refinery) {
        if ( refinery == null ) {
            return null;
        }

        RefineryEntity refineryEntity = new RefineryEntity();

        refineryEntity.setId( refinery.getId() );
        refineryEntity.setPlaceType( refinery.getPlaceType() );
        refineryEntity.setCreatedAt( refinery.getCreatedAt() );
        refineryEntity.setUpdatedAt( refinery.getUpdatedAt() );
        refineryEntity.setName( refinery.getName() );
        refineryEntity.setTranslation( refinery.getTranslation() );

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
        refinery.setTranslation( refineryEntity.getTranslation() );
        refinery.setPlaceType( refineryEntity.getPlaceType() );
        refinery.setCreatedAt( refineryEntity.getCreatedAt() );
        refinery.setUpdatedAt( refineryEntity.getUpdatedAt() );

        return refinery;
    }
}
