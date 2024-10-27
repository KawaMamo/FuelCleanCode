package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateSellerPaymentRequest;
import org.example.contract.request.update.UpdateSellerPaymentRequest;
import org.example.contract.response.SellerPaymentResponse;
import org.example.model.SellerPayment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-27T22:43:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class SellerPaymentDomainMapperImpl implements SellerPaymentDomainMapper {

    @Override
    public SellerPayment requestToDomain(CreateSellerPaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        SellerPayment sellerPayment = new SellerPayment();

        sellerPayment.setAmount( request.getAmount() );
        sellerPayment.setBillNumber( request.getBillNumber() );
        sellerPayment.setCreatedAt( request.getCreatedAt() );
        sellerPayment.setNotes( request.getNotes() );

        return sellerPayment;
    }

    @Override
    public SellerPayment requestToDomain(UpdateSellerPaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        SellerPayment sellerPayment = new SellerPayment();

        sellerPayment.setId( request.getId() );
        sellerPayment.setAmount( request.getAmount() );
        sellerPayment.setBillNumber( request.getBillNumber() );
        sellerPayment.setCreatedAt( request.getCreatedAt() );
        sellerPayment.setNotes( request.getNotes() );

        return sellerPayment;
    }

    @Override
    public SellerPaymentResponse domainToResponse(SellerPayment domain) {
        if ( domain == null ) {
            return null;
        }

        SellerPaymentResponse sellerPaymentResponse = new SellerPaymentResponse();

        sellerPaymentResponse.setId( domain.getId() );
        sellerPaymentResponse.setAmount( domain.getAmount() );
        sellerPaymentResponse.setBillNumber( domain.getBillNumber() );
        sellerPaymentResponse.setCreatedAt( domain.getCreatedAt() );
        sellerPaymentResponse.setUpdatedAt( domain.getUpdatedAt() );
        sellerPaymentResponse.setNotes( domain.getNotes() );
        sellerPaymentResponse.setSeller( domain.getSeller() );

        return sellerPaymentResponse;
    }
}
