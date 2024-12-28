package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateBuyerRequest;
import org.example.contract.request.update.UpdateBuyerRequest;
import org.example.contract.response.BuyerResponse;
import org.example.model.Buyer;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-02T23:23:06+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class BuyerDomainMapperImpl implements BuyerDomainMapper {

    @Override
    public Buyer requestToDomain(CreateBuyerRequest request) {
        if ( request == null ) {
            return null;
        }

        Buyer buyer = new Buyer();

        buyer.setName( request.getName() );
        buyer.setOrganization( request.getOrganization() );

        return buyer;
    }

    @Override
    public Buyer requestToDomain(UpdateBuyerRequest request) {
        if ( request == null ) {
            return null;
        }

        Buyer buyer = new Buyer();

        buyer.setId( request.getId() );
        buyer.setName( request.getName() );
        buyer.setOrganization( request.getOrganization() );

        return buyer;
    }

    @Override
    public BuyerResponse domainToResponse(Buyer buyer) {
        if ( buyer == null ) {
            return null;
        }

        BuyerResponse buyerResponse = new BuyerResponse();

        buyerResponse.setId( buyer.getId() );
        buyerResponse.setName( buyer.getName() );
        buyerResponse.setOrganization( buyer.getOrganization() );
        buyerResponse.setCreatedAt( buyer.getCreatedAt() );
        buyerResponse.setUpdatedAt( buyer.getUpdatedAt() );

        return buyerResponse;
    }
}
