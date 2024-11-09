package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.model.Seller;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-08T21:17:44+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class SellerMapperImpl implements SellerMapper {

    @Override
    public org.example.entities.Seller domainToEntity(Seller seller) {
        if ( seller == null ) {
            return null;
        }

        org.example.entities.Seller seller1 = new org.example.entities.Seller();

        seller1.setId( seller.getId() );
        seller1.setName( seller.getName() );
        seller1.setCreatedAt( seller.getCreatedAt() );
        seller1.setUpdatedAt( seller.getUpdatedAt() );

        return seller1;
    }

    @Override
    public Seller entityToDomain(org.example.entities.Seller seller) {
        if ( seller == null ) {
            return null;
        }

        Seller seller1 = new Seller();

        seller1.setId( seller.getId() );
        seller1.setName( seller.getName() );
        seller1.setCreatedAt( seller.getCreatedAt() );
        seller1.setUpdatedAt( seller.getUpdatedAt() );

        return seller1;
    }
}
