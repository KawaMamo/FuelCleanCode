package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.model.TransLine;
import org.example.model.TransLog;
import org.example.model.Transportation;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-15T14:52:12+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransLogDomainMapperImpl implements TransLogDomainMapper {

    @Override
    public TransLog requestToDomain(CreateTransLogRequest request) {
        if ( request == null ) {
            return null;
        }

        TransLog transLog = new TransLog();

        transLog.setVehicle( createTransLogRequestToVehicle( request ) );
        transLog.setTransLine( createTransLogRequestToTransLine( request ) );
        transLog.setTransportation( createTransLogRequestToTransportation( request ) );
        transLog.setFees( request.getFees() );
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
        transLog.setTransportation( updateTransLogRequestToTransportation( request ) );
        transLog.setId( request.getId() );
        transLog.setFees( request.getFees() );
        transLog.setNotes( request.getNotes() );

        return transLog;
    }

    protected Vehicle createTransLogRequestToVehicle(CreateTransLogRequest createTransLogRequest) {
        if ( createTransLogRequest == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( createTransLogRequest.getVehicleId() );

        return vehicle;
    }

    protected TransLine createTransLogRequestToTransLine(CreateTransLogRequest createTransLogRequest) {
        if ( createTransLogRequest == null ) {
            return null;
        }

        TransLine transLine = new TransLine();

        transLine.setId( createTransLogRequest.getTransLineId() );

        return transLine;
    }

    protected Transportation createTransLogRequestToTransportation(CreateTransLogRequest createTransLogRequest) {
        if ( createTransLogRequest == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setId( createTransLogRequest.getTransportationId() );

        return transportation;
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

    protected Transportation updateTransLogRequestToTransportation(UpdateTransLogRequest updateTransLogRequest) {
        if ( updateTransLogRequest == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setId( updateTransLogRequest.getTransportationId() );

        return transportation;
    }
}
