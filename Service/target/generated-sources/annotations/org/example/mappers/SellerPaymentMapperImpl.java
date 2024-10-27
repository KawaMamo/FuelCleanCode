package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.SellerPayment;
import org.example.model.Seller;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-27T16:55:27+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class SellerPaymentMapperImpl implements SellerPaymentMapper {

    @Override
    public SellerPayment domainToEntity(org.example.model.SellerPayment sellerPayment) {
        if ( sellerPayment == null ) {
            return null;
        }

        SellerPayment sellerPayment1 = new SellerPayment();

        sellerPayment1.setId( sellerPayment.getId() );
        sellerPayment1.setBillNumber( sellerPayment.getBillNumber() );
        sellerPayment1.setCreatedAt( sellerPayment.getCreatedAt() );
        sellerPayment1.setNotes( sellerPayment.getNotes() );
        sellerPayment1.setSeller( sellerToSeller( sellerPayment.getSeller() ) );

        return sellerPayment1;
    }

    @Override
    public org.example.model.SellerPayment entityToDomain(SellerPayment sellerPayment) {
        if ( sellerPayment == null ) {
            return null;
        }

        org.example.model.SellerPayment sellerPayment1 = new org.example.model.SellerPayment();

        sellerPayment1.setId( sellerPayment.getId() );
        sellerPayment1.setBillNumber( sellerPayment.getBillNumber() );
        sellerPayment1.setCreatedAt( sellerPayment.getCreatedAt() );
        sellerPayment1.setNotes( sellerPayment.getNotes() );
        sellerPayment1.setSeller( sellerToSeller1( sellerPayment.getSeller() ) );

        return sellerPayment1;
    }

    protected org.example.entities.Seller sellerToSeller(Seller seller) {
        if ( seller == null ) {
            return null;
        }

        org.example.entities.Seller seller1 = new org.example.entities.Seller();

        seller1.setId( seller.getId() );
        seller1.setName( seller.getName() );

        return seller1;
    }

    protected Seller sellerToSeller1(org.example.entities.Seller seller) {
        if ( seller == null ) {
            return null;
        }

        Seller seller1 = new Seller();

        seller1.setId( seller.getId() );
        seller1.setName( seller.getName() );

        return seller1;
    }
}
