package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.model.TransLog;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-15T22:39:34+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransLogDomainMapperImpl implements TransLogDomainMapper {

    @Override
    public TransLog requestToDomain(CreateTransLogRequest request) {
        if ( request == null ) {
            return null;
        }

        TransLog transLog = new TransLog();

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

        return transLogResponse;
    }
}
