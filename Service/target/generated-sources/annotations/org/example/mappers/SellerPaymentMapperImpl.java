package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.SellerPayment;
import org.example.model.Money;
import org.example.model.Seller;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-31T23:55:24+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class SellerPaymentMapperImpl implements SellerPaymentMapper {

    @Override
    public SellerPayment domainToEntity(org.example.model.SellerPayment sellerPayment) {
        if ( sellerPayment == null ) {
            return null;
        }

        SellerPayment sellerPayment1 = new SellerPayment();

        sellerPayment1.setPriceAmount( sellerPaymentAmountAmount( sellerPayment ) );
        sellerPayment1.setPriceCurrency( sellerPaymentAmountCurrency( sellerPayment ) );
        sellerPayment1.setId( sellerPayment.getId() );
        sellerPayment1.setBillNumber( sellerPayment.getBillNumber() );
        sellerPayment1.setCreatedAt( sellerPayment.getCreatedAt() );
        sellerPayment1.setUpdatedAt( sellerPayment.getUpdatedAt() );
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

        sellerPayment1.setAmount( sellerPaymentToMoney( sellerPayment ) );
        sellerPayment1.setId( sellerPayment.getId() );
        sellerPayment1.setBillNumber( sellerPayment.getBillNumber() );
        sellerPayment1.setCreatedAt( sellerPayment.getCreatedAt() );
        sellerPayment1.setUpdatedAt( sellerPayment.getUpdatedAt() );
        sellerPayment1.setNotes( sellerPayment.getNotes() );
        sellerPayment1.setSeller( sellerToSeller1( sellerPayment.getSeller() ) );

        return sellerPayment1;
    }

    private Double sellerPaymentAmountAmount(org.example.model.SellerPayment sellerPayment) {
        if ( sellerPayment == null ) {
            return null;
        }
        Money amount = sellerPayment.getAmount();
        if ( amount == null ) {
            return null;
        }
        Double amount1 = amount.getAmount();
        if ( amount1 == null ) {
            return null;
        }
        return amount1;
    }

    private String sellerPaymentAmountCurrency(org.example.model.SellerPayment sellerPayment) {
        if ( sellerPayment == null ) {
            return null;
        }
        Money amount = sellerPayment.getAmount();
        if ( amount == null ) {
            return null;
        }
        String currency = amount.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    protected org.example.entities.Seller sellerToSeller(Seller seller) {
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

    protected Money sellerPaymentToMoney(SellerPayment sellerPayment) {
        if ( sellerPayment == null ) {
            return null;
        }

        Money money = new Money();

        money.setAmount( sellerPayment.getPriceAmount() );
        money.setCurrency( sellerPayment.getPriceCurrency() );

        return money;
    }

    protected Seller sellerToSeller1(org.example.entities.Seller seller) {
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
