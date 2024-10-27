package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.OfficeEntity;
import org.example.entities.OfficePayment;
import org.example.model.Office;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-27T22:43:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class OfficePaymentMapperImpl implements OfficePaymentMapper {

    @Override
    public OfficePayment domainToEntity(org.example.model.OfficePayment officePayment) {
        if ( officePayment == null ) {
            return null;
        }

        OfficePayment officePayment1 = new OfficePayment();

        officePayment1.setId( officePayment.getId() );
        officePayment1.setBillNumber( officePayment.getBillNumber() );
        officePayment1.setCreatedAt( officePayment.getCreatedAt() );
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

        officePayment1.setId( officePayment.getId() );
        officePayment1.setBillNumber( officePayment.getBillNumber() );
        officePayment1.setCreatedAt( officePayment.getCreatedAt() );
        officePayment1.setNotes( officePayment.getNotes() );
        officePayment1.setOffice( officeEntityToOffice( officePayment.getOffice() ) );

        return officePayment1;
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
