package org.example.mappers;

import org.example.entities.TransLogEntity;
import org.example.entities.TransportationEntity;
import org.example.model.TransLog;
import org.example.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = TransLineMapper.class)
public interface TransLogMapper {
    @Mapping(source = "fees.currency", target = "feesCurrency")
    @Mapping(source = "fees.amount", target = "feesAmount")
    TransLogEntity domainToEntity(TransLog transLog);
    @Mapping(target = "fees.currency", source = "feesCurrency")
    @Mapping(target = "fees.amount", source = "feesAmount")
    @Mapping(source = "transportation", target = "transportation", qualifiedByName = "mapId")
    TransLog entityToDomain(TransLogEntity transLog);

    @Named("mapId")
    static Transportation mapId(TransportationEntity transportationEntity){
        final Transportation transportation = new Transportation();
        transportation.setId(transportationEntity.getId());
        return transportation;
    }

    //TODO: fix the error message
}
