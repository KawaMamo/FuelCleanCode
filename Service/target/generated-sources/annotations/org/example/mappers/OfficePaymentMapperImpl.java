package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.OfficeEntity;
import org.example.entities.OfficePayment;
import org.example.model.Money;
import org.example.model.Office;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T14:23:16+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class OfficePaymentMapperImpl implements OfficePaymentMapper {

    @Override
    public OfficePayment domainToEntity(org.example.model.OfficePayment officePayment) {
        if ( officePayment == null ) {
            return null;
        }

        OfficePayment officePayment1 = new OfficePayment();

        officePayment1.setPriceAmount( officePaymentAmountAmount( officePayment ) );
        officePayment1.setPriceCurrency( officePaymentAmountCurrency( officePayment ) );
        officePayment1.setId( officePayment.getId() );
        officePayment1.setBillNumber( officePayment.getBillNumber() );
        officePayment1.setCreatedAt( officePayment.getCreatedAt() );
        officePayment1.setUpdatedAt( officePayment.getUpdatedAt() );
        officePayment1.setNotes( officePayment.getNotes() );
        officePayment1.setOffice( officeToOfficeEntity( officePayment.getOffice() ) );

        return officePayment1;
    }

    @Override
    public org.example.model.OfficePayment entityToDomain(OfficePayment officePayment) {
        if ( officePayment == null ) {
            return null;
        }

        org.example.model.OfficePayment officePayment1 = new org.example.model.OfficePayment();

        officePayment1.setAmount( officePaymentToMoney( officePayment ) );
        officePayment1.setId( officePayment.getId() );
        officePayment1.setBillNumber( officePayment.getBillNumber() );
        officePayment1.setCreatedAt( officePayment.getCreatedAt() );
        officePayment1.setUpdatedAt( officePayment.getUpdatedAt() );
        officePayment1.setNotes( officePayment.getNotes() );
        officePayment1.setOffice( officeEntityToOffice( officePayment.getOffice() ) );

        return officePayment1;
    }

    private Double officePaymentAmountAmount(org.example.model.OfficePayment officePayment) {
        if ( officePayment == null ) {
            return null;
        }
        Money amount = officePayment.getAmount();
        if ( amount == null ) {
            return null;
        }
        Double amount1 = amount.getAmount();
        if ( amount1 == null ) {
            return null;
        }
        return amount1;
    }

    private String officePaymentAmountCurrency(org.example.model.OfficePayment officePayment) {
        if ( officePayment == null ) {
            return null;
        }
        Money amount = officePayment.getAmount();
        if ( amount == null ) {
            return null;
        }
        String currency = amount.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    protected OfficeEntity officeToOfficeEntity(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeEntity officeEntity = new OfficeEntity();

        officeEntity.setId( office.getId() );
        officeEntity.setName( office.getName() );
        officeEntity.setCreatedAt( office.getCreatedAt() );
        officeEntity.setUpdateAt( office.getUpdateAt() );

        return officeEntity;
    }

    protected Money officePaymentToMoney(OfficePayment officePayment) {
        if ( officePayment == null ) {
            return null;
        }

        Money money = new Money();

        money.setAmount( officePayment.getPriceAmount() );
        money.setCurrency( officePayment.getPriceCurrency() );

        return money;
    }

    protected Office officeEntityToOffice(OfficeEntity officeEntity) {
        if ( officeEntity == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( officeEntity.getId() );
        office.setName( officeEntity.getName() );
        office.setCreatedAt( officeEntity.getCreatedAt() );
        office.setUpdateAt( officeEntity.getUpdateAt() );

        return office;
    }
}
