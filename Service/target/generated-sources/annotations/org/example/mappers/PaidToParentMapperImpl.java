package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.PaidToParent;
import org.example.model.Money;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-31T16:24:08+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class PaidToParentMapperImpl implements PaidToParentMapper {

    @Override
    public PaidToParent domainToEntity(org.example.model.PaidToParent domain) {
        if ( domain == null ) {
            return null;
        }

        PaidToParent paidToParent = new PaidToParent();

        paidToParent.setPriceAmount( domainAmountAmount( domain ) );
        paidToParent.setPriceCurrency( domainAmountCurrency( domain ) );
        paidToParent.setId( domain.getId() );
        paidToParent.setBillNumber( domain.getBillNumber() );
        paidToParent.setCreatedAt( domain.getCreatedAt() );
        paidToParent.setUpdatedAt( domain.getUpdatedAt() );
        paidToParent.setNotes( domain.getNotes() );

        return paidToParent;
    }

    @Override
    public org.example.model.PaidToParent entityToDomain(PaidToParent entity) {
        if ( entity == null ) {
            return null;
        }

        org.example.model.PaidToParent paidToParent = new org.example.model.PaidToParent();

        paidToParent.setAmount( paidToParentToMoney( entity ) );
        paidToParent.setId( entity.getId() );
        paidToParent.setBillNumber( entity.getBillNumber() );
        paidToParent.setCreatedAt( entity.getCreatedAt() );
        paidToParent.setUpdatedAt( entity.getUpdatedAt() );
        paidToParent.setNotes( entity.getNotes() );

        return paidToParent;
    }

    private Double domainAmountAmount(org.example.model.PaidToParent paidToParent) {
        if ( paidToParent == null ) {
            return null;
        }
        Money amount = paidToParent.getAmount();
        if ( amount == null ) {
            return null;
        }
        Double amount1 = amount.getAmount();
        if ( amount1 == null ) {
            return null;
        }
        return amount1;
    }

    private String domainAmountCurrency(org.example.model.PaidToParent paidToParent) {
        if ( paidToParent == null ) {
            return null;
        }
        Money amount = paidToParent.getAmount();
        if ( amount == null ) {
            return null;
        }
        String currency = amount.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    protected Money paidToParentToMoney(PaidToParent paidToParent) {
        if ( paidToParent == null ) {
            return null;
        }

        Money money = new Money();

        money.setAmount( paidToParent.getPriceAmount() );
        money.setCurrency( paidToParent.getPriceCurrency() );

        return money;
    }
}
