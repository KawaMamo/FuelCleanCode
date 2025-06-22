package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.contract.response.ClientPaymentResponse;
import org.example.model.ClientPayment;
import org.example.model.GasStation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-22T17:50:21+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class ClientPaymentDomainMapperImpl implements ClientPaymentDomainMapper {

    @Override
    public ClientPayment requestToDomain(CreateClientPaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        ClientPayment clientPayment = new ClientPayment();

        clientPayment.setGasStation( createClientPaymentRequestToGasStation( request ) );
        clientPayment.setAmount( request.getAmount() );
        clientPayment.setBillNumber( request.getBillNumber() );
        clientPayment.setNotes( request.getNotes() );

        return clientPayment;
    }

    @Override
    public ClientPayment requestToDomain(UpdateClientPaymentRequest request) {
        if ( request == null ) {
            return null;
        }

        ClientPayment clientPayment = new ClientPayment();

        clientPayment.setGasStation( updateClientPaymentRequestToGasStation( request ) );
        clientPayment.setId( request.getId() );
        clientPayment.setAmount( request.getAmount() );
        clientPayment.setBillNumber( request.getBillNumber() );
        clientPayment.setNotes( request.getNotes() );

        return clientPayment;
    }

    @Override
    public ClientPaymentResponse domainToResponse(ClientPayment domain) {
        if ( domain == null ) {
            return null;
        }

        ClientPaymentResponse clientPaymentResponse = new ClientPaymentResponse();

        clientPaymentResponse.setId( domain.getId() );
        clientPaymentResponse.setAmount( domain.getAmount() );
        clientPaymentResponse.setBillNumber( domain.getBillNumber() );
        clientPaymentResponse.setCreatedAt( domain.getCreatedAt() );
        clientPaymentResponse.setUpdatedAt( domain.getUpdatedAt() );
        clientPaymentResponse.setNotes( domain.getNotes() );
        clientPaymentResponse.setGasStation( domain.getGasStation() );

        return clientPaymentResponse;
    }

    protected GasStation createClientPaymentRequestToGasStation(CreateClientPaymentRequest createClientPaymentRequest) {
        if ( createClientPaymentRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( createClientPaymentRequest.getGasStationId() );

        return gasStation;
    }

    protected GasStation updateClientPaymentRequestToGasStation(UpdateClientPaymentRequest updateClientPaymentRequest) {
        if ( updateClientPaymentRequest == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( updateClientPaymentRequest.getGasStationId() );

        return gasStation;
    }
}
