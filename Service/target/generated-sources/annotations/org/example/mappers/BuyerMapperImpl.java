package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.model.Buyer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-03T15:45:24+0300",
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
        buyer.setCreatedAt( domain.getCreatedAt() );
        buyer.setUpdatedAt( domain.getUpdatedAt() );

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
        buyer.setCreatedAt( entity.getCreatedAt() );
        buyer.setUpdatedAt( entity.getUpdatedAt() );

        return buyer;
    }
}
