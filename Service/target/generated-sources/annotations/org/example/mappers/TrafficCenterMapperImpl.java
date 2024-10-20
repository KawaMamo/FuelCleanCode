package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.TrafficCenterEntity;
import org.example.model.TrafficCenter;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-20T15:31:02+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TrafficCenterMapperImpl implements TrafficCenterMapper {

    @Override
    public TrafficCenterEntity domainToEntity(TrafficCenter trafficCenter) {
        if ( trafficCenter == null ) {
            return null;
        }

        TrafficCenterEntity trafficCenterEntity = new TrafficCenterEntity();

        trafficCenterEntity.setId( trafficCenter.getId() );
        trafficCenterEntity.setName( trafficCenter.getName() );
        trafficCenterEntity.setCreatedAt( trafficCenter.getCreatedAt() );
        trafficCenterEntity.setUpdatedAt( trafficCenter.getUpdatedAt() );

        return trafficCenterEntity;
    }

    @Override
    public TrafficCenter entityToDomain(TrafficCenterEntity trafficCenter) {
        if ( trafficCenter == null ) {
            return null;
        }

        TrafficCenter trafficCenter1 = new TrafficCenter();

        trafficCenter1.setId( trafficCenter.getId() );
        trafficCenter1.setName( trafficCenter.getName() );
        trafficCenter1.setCreatedAt( trafficCenter.getCreatedAt() );
        trafficCenter1.setUpdatedAt( trafficCenter.getUpdatedAt() );

        return trafficCenter1;
    }
}
