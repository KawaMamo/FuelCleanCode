package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateFatTransLineRequest;
import org.example.contract.response.TransLineResponse;
import org.example.model.Money;
import org.example.model.TransLine;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-06T21:31:57+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
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

        return transLineResponse;
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
}
