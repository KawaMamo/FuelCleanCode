package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.contract.response.OfficePaymentResponse;
import org.example.model.OfficePayment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-27T22:43:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class OfficePaymentDomainMapperImpl implements OfficePaymentDomainMapper {

    @Override
    public OfficePayment requestToDomain(CreateOfficePaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        OfficePayment officePayment = new OfficePayment();

        officePayment.setAmount( request.getAmount() );
        officePayment.setBillNumber( request.getBillNumber() );
        officePayment.setNotes( request.getNotes() );

        return officePayment;
    }

    @Override
    public OfficePayment requestToDomain(UpdateOfficePaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        OfficePayment officePayment = new OfficePayment();

        officePayment.setId( request.getId() );
        officePayment.setAmount( request.getAmount() );
        officePayment.setBillNumber( request.getBillNumber() );
        officePayment.setNotes( request.getNotes() );

        return officePayment;
    }

    @Override
    public OfficePaymentResponse domainToResponse(OfficePayment officePayment) {
        if ( officePayment == null ) {
            return null;
        }

        OfficePaymentResponse officePaymentResponse = new OfficePaymentResponse();

        officePaymentResponse.setId( officePayment.getId() );
        officePaymentResponse.setAmount( officePayment.getAmount() );
        officePaymentResponse.setBillNumber( officePayment.getBillNumber() );
        officePaymentResponse.setCreatedAt( officePayment.getCreatedAt() );
        officePaymentResponse.setUpdatedAt( officePayment.getUpdatedAt() );
        officePaymentResponse.setNotes( officePayment.getNotes() );
        officePaymentResponse.setOffice( officePayment.getOffice() );

        return officePaymentResponse;
    }
}
