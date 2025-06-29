package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.FatCreateTransLogRequest;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.model.TransLine;
import org.example.model.TransLog;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-30T00:01:40+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransLogDomainMapperImpl implements TransLogDomainMapper {

    @Override
    public TransLog requestToDomain(FatCreateTransLogRequest request) {
        if ( request == null ) {
            return null;
        }

        TransLog transLog = new TransLog();

        transLog.setVehicle( fatCreateTransLogRequestToVehicle( request ) );
        transLog.setTransLine( fatCreateTransLogRequestToTransLine( request ) );
        transLog.setFees( request.getFees() );
        transLog.setTransportation( request.getTransportation() );
        transLog.setNotes( request.getNotes() );

        return transLog;
    }

    @Override
    public TransLogResponse domainToResponse(TransLog transLog) {
        if ( transLog == null ) {
            return null;
        }

        TransLogResponse transLogResponse = new TransLogResponse();

        transLogResponse.setId( transLog.getId() );
        transLogResponse.setVehicle( transLog.getVehicle() );
        transLogResponse.setTransLine( transLog.getTransLine() );
        transLogResponse.setFees( transLog.getFees() );
        transLogResponse.setTransportation( transLog.getTransportation() );
        transLogResponse.setNotes( transLog.getNotes() );
        transLogResponse.setCreatedAt( transLog.getCreatedAt() );
        transLogResponse.setUpdatedAt( transLog.getUpdatedAt() );

        return transLogResponse;
    }

    @Override
    public TransLog requestToDomain(UpdateTransLogRequest request) {
        if ( request == null ) {
            return null;
        }

        TransLog transLog = new TransLog();

        transLog.setVehicle( updateTransLogRequestToVehicle( request ) );
        transLog.setTransLine( updateTransLogRequestToTransLine( request ) );
        transLog.setId( request.getId() );
        transLog.setFees( request.getFees() );
        transLog.setNotes( request.getNotes() );

        return transLog;
    }

    protected Vehicle fatCreateTransLogRequestToVehicle(FatCreateTransLogRequest fatCreateTransLogRequest) {
        if ( fatCreateTransLogRequest == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( fatCreateTransLogRequest.getVehicleId() );

        return vehicle;
    }

    protected TransLine fatCreateTransLogRequestToTransLine(FatCreateTransLogRequest fatCreateTransLogRequest) {
        if ( fatCreateTransLogRequest == null ) {
            return null;
        }

        TransLine transLine = new TransLine();

        transLine.setId( fatCreateTransLogRequest.getTransLineId() );

        return transLine;
    }

    protected Vehicle updateTransLogRequestToVehicle(UpdateTransLogRequest updateTransLogRequest) {
        if ( updateTransLogRequest == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( updateTransLogRequest.getVehicleId() );

        return vehicle;
    }

    protected TransLine updateTransLogRequestToTransLine(UpdateTransLogRequest updateTransLogRequest) {
        if ( updateTransLogRequest == null ) {
            return null;
        }

        TransLine transLine = new TransLine();

        transLine.setId( updateTransLogRequest.getTransLineId() );

        return transLine;
    }
}
