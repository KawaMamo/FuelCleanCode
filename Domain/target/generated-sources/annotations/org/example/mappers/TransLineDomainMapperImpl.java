package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateFatTransLineRequest;
import org.example.contract.request.update.UpdateTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.model.Money;
import org.example.model.TransLine;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-01T15:58:45+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class TransLineDomainMapperImpl implements TransLineDomainMapper {

    @Override
    public TransLine requestToDomain(CreateFatTransLineRequest request) {
        if ( request == null ) {
            return null;
        }

        TransLine transLine = new TransLine();

        transLine.setFee( createFatTransLineRequestToMoney( request ) );
        transLine.setSource( request.getSource() );
        transLine.setDestination( request.getDestination() );

        return transLine;
    }

    @Override
    public TransLineResponse domainToResponse(TransLine transLine) {
        if ( transLine == null ) {
            return null;
        }

        TransLineResponse transLineResponse = new TransLineResponse();

        transLineResponse.setId( transLine.getId() );
        transLineResponse.setSource( transLine.getSource() );
        transLineResponse.setDestination( transLine.getDestination() );
        transLineResponse.setFee( transLine.getFee() );
        transLineResponse.setCreatedAt( transLine.getCreatedAt() );
        transLineResponse.setUpdatedAt( transLine.getUpdatedAt() );

        return transLineResponse;
    }

    @Override
    public TransLine requestToDomain(UpdateTransLineRequest request) {
        if ( request == null ) {
            return null;
        }

        TransLine transLine = new TransLine();

        transLine.setFee( updateTransLineRequestToMoney( request ) );
        transLine.setId( request.getId() );

        return transLine;
    }

    protected Money createFatTransLineRequestToMoney(CreateFatTransLineRequest createFatTransLineRequest) {
        if ( createFatTransLineRequest == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( createFatTransLineRequest.getFeeCurrency() );
        money.setAmount( createFatTransLineRequest.getFeeAmount() );

        return money;
    }

    protected Money updateTransLineRequestToMoney(UpdateTransLineRequest updateTransLineRequest) {
        if ( updateTransLineRequest == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( updateTransLineRequest.getFeeCurrency() );
        money.setAmount( updateTransLineRequest.getFeeAmount() );

        return money;
    }
}
