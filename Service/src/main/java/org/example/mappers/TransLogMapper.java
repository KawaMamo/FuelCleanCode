package org.example.mappers;

import org.example.entities.TransLogEntity;
import org.example.entities.TransferMaterialsEntity;
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationReasonEntity;
import org.example.model.TransLog;
import org.example.model.TransferMaterials;
import org.example.model.Transportation;
import org.example.model.TransportationReason;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = TransLineMapper.class)
public interface TransLogMapper {
    @Mapping(source = "fees.currency", target = "feesCurrency")
    @Mapping(source = "fees.amount", target = "feesAmount")
    @Mapping(source = "transportation", target = "transportation", qualifiedByName = "transDomainToEntity")
    TransLogEntity domainToEntity(TransLog transLog);
    @Mapping(target = "fees.currency", source = "feesCurrency")
    @Mapping(target = "fees.amount", source = "feesAmount")
    @Mapping(source = "transportation", target = "transportation", qualifiedByName = "transEntityToDomain")
    TransLog entityToDomain(TransLogEntity transLog);

    @Named("transEntityToDomain")
    static TransportationReason transEntityToDomain(TransportationReasonEntity transportation){
        TransportationReason transportationReason;
        if(transportation instanceof TransportationEntity) {
            Transportation transportationDomain = new Transportation();
            transportationDomain.setId(transportation.getId());
            transportationReason = transportationDomain;
        }else if(transportation instanceof TransferMaterialsEntity){
            TransferMaterials transferMaterials = new TransferMaterials();
            transferMaterials.setId(transportation.getId());
            transportationReason = transferMaterials;
        }else {
            transportationReason = null;
        }

        return transportationReason;
    }

    //TODO: fix the error message

    @Named("transDomainToEntity")
    static TransportationReasonEntity transDomainToEntity(TransportationReason domain){
        if(domain instanceof Transportation){
            final TransportationEntity transportationEntity = new TransportationEntity();
            transportationEntity.setId(domain.getId());
            return transportationEntity;
        }else if(domain instanceof TransferMaterials){
            final TransferMaterialsEntity transferMaterialsEntity = new TransferMaterialsEntity();
            transferMaterialsEntity.setId(domain.getId());
            return transferMaterialsEntity;
        }
        return null;
    }
}
