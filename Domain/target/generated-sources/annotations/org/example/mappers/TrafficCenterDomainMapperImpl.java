package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateTrafficCenterRequest;
import org.example.contract.response.TrafficCenterResponse;
import org.example.model.TrafficCenter;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-06T19:26:05+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TrafficCenterDomainMapperImpl implements TrafficCenterDomainMapper {

    @Override
    public TrafficCenter requestToDomain(CreateTrafficCenterRequest request) {
        if ( request == null ) {
            return null;
        }

        TrafficCenter trafficCenter = new TrafficCenter();

        trafficCenter.setName( request.getName() );

        return trafficCenter;
    }

    @Override
    public TrafficCenterResponse domainToResponse(TrafficCenter trafficCenter) {
        if ( trafficCenter == null ) {
            return null;
        }

        TrafficCenterResponse trafficCenterResponse = new TrafficCenterResponse();

        trafficCenterResponse.setId( trafficCenter.getId() );
        trafficCenterResponse.setName( trafficCenter.getName() );
        trafficCenterResponse.setCreatedAt( trafficCenter.getCreatedAt() );

        return trafficCenterResponse;
    }
}
