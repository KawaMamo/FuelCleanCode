package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateOfficePaymentRequest;
import org.example.contract.request.update.UpdateOfficePaymentRequest;
import org.example.contract.response.OfficePaymentResponse;
import org.example.model.Office;
import org.example.model.OfficePayment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-03T23:27:48+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class OfficePaymentDomainMapperImpl implements OfficePaymentDomainMapper {

    @Override
    public OfficePayment requestToDomain(CreateOfficePaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        OfficePayment officePayment = new OfficePayment();

        officePayment.setOffice( createOfficePaymentRequestToOffice( request ) );
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

        officePayment.setOffice( updateOfficePaymentRequestToOffice( request ) );
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

    protected Office createOfficePaymentRequestToOffice(CreateOfficePaymentRequest createOfficePaymentRequest) {
        if ( createOfficePaymentRequest == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( createOfficePaymentRequest.getOfficeId() );

        return office;
    }

    protected Office updateOfficePaymentRequestToOffice(UpdateOfficePaymentRequest updateOfficePaymentRequest) {
        if ( updateOfficePaymentRequest == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( updateOfficePaymentRequest.getOfficeId() );

        return office;
    }
}
