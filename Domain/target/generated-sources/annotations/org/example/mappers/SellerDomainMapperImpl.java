package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateSellerRequest;
import org.example.contract.request.update.UpdateSellerRequest;
import org.example.contract.response.SellerResponse;
import org.example.model.Seller;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T00:01:40+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class SellerDomainMapperImpl implements SellerDomainMapper {

    @Override
    public Seller requestToDomain(CreateSellerRequest request) {
        if ( request == null ) {
            return null;
        }

        Seller seller = new Seller();

        seller.setName( request.getName() );

        return seller;
    }

    @Override
    public Seller requestToDomain(UpdateSellerRequest request) {
        if ( request == null ) {
            return null;
        }

        Seller seller = new Seller();

        seller.setId( request.getId() );
        seller.setName( request.getName() );

        return seller;
    }

    @Override
    public SellerResponse domainToResponse(Seller seller) {
        if ( seller == null ) {
            return null;
        }

        SellerResponse sellerResponse = new SellerResponse();

        sellerResponse.setId( seller.getId() );
        sellerResponse.setName( seller.getName() );
        sellerResponse.setCreatedAt( seller.getCreatedAt() );
        sellerResponse.setUpdatedAt( seller.getUpdatedAt() );

        return sellerResponse;
    }
}
