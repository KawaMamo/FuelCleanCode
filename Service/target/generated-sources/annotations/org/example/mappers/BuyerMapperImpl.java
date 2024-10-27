package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.model.Buyer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-25T18:02:03+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class BuyerMapperImpl implements BuyerMapper {

    @Override
    public org.example.entities.Buyer domainToEntity(Buyer domain) {
        if ( domain == null ) {
            return null;
        }

        org.example.entities.Buyer buyer = new org.example.entities.Buyer();

        buyer.setId( domain.getId() );
        buyer.setName( domain.getName() );
        buyer.setOrganization( domain.getOrganization() );

        return buyer;
    }

    @Override
    public Buyer entityToDomain(org.example.entities.Buyer entity) {
        if ( entity == null ) {
            return null;
        }

        Buyer buyer = new Buyer();

        buyer.setId( entity.getId() );
        buyer.setName( entity.getName() );
        buyer.setOrganization( entity.getOrganization() );

        return buyer;
    }
}
