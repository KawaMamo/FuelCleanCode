package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreatePaidToParentRequest;
import org.example.contract.request.update.UpdatePaidToParentRequest;
import org.example.contract.response.PaidToParentResponse;
import org.example.model.PaidToParent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-27T16:55:25+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class PaidToParentDomainMapperImpl implements PaidToParentDomainMapper {

    @Override
    public PaidToParent requestToDomain(CreatePaidToParentRequest request) {
        if ( request == null ) {
            return null;
        }

        PaidToParent paidToParent = new PaidToParent();

        paidToParent.setAmount( request.getAmount() );
        paidToParent.setBillNumber( request.getBillNumber() );
        paidToParent.setCreatedAt( request.getCreatedAt() );
        paidToParent.setNotes( request.getNotes() );

        return paidToParent;
    }

    @Override
    public PaidToParent requestToDomain(UpdatePaidToParentRequest request) {
        if ( request == null ) {
            return null;
        }

        PaidToParent paidToParent = new PaidToParent();

        paidToParent.setId( request.getId() );
        paidToParent.setAmount( request.getAmount() );
        paidToParent.setBillNumber( request.getBillNumber() );
        paidToParent.setCreatedAt( request.getCreatedAt() );
        paidToParent.setNotes( request.getNotes() );

        return paidToParent;
    }

    @Override
    public PaidToParentResponse domainToResponse(PaidToParent domain) {
        if ( domain == null ) {
            return null;
        }

        PaidToParentResponse paidToParentResponse = new PaidToParentResponse();

        paidToParentResponse.setId( domain.getId() );
        paidToParentResponse.setAmount( domain.getAmount() );
        paidToParentResponse.setBillNumber( domain.getBillNumber() );
        paidToParentResponse.setCreatedAt( domain.getCreatedAt() );
        paidToParentResponse.setUpdatedAt( domain.getUpdatedAt() );
        paidToParentResponse.setNotes( domain.getNotes() );

        return paidToParentResponse;
    }
}
