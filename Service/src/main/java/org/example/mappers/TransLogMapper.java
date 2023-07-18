package org.example.mappers;

import org.example.entities.TransLogEntity;
import org.example.model.TransLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = TransLineMapper.class)
public interface TransLogMapper {
    @Mapping(source = "fees.currency", target = "feesCurrency")
    @Mapping(source = "fees.amount", target = "feesAmount")
    TransLogEntity domainToEntity(TransLog transLog);
    @Mapping(target = "fees.currency", source = "feesCurrency")
    @Mapping(target = "fees.amount", source = "feesAmount")
    TransLog entityToDomain(TransLogEntity transLog);
}
