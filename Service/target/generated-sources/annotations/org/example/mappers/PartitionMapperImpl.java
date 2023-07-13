package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.PartitionEntity;
import org.example.model.GasStation;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.Partition;
import org.example.model.Transportation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T01:12:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class PartitionMapperImpl implements PartitionMapper {

    @Override
    public Partition entityToDomain(PartitionEntity partition) {
        if ( partition == null ) {
            return null;
        }

        Partition partition1 = new Partition();

        partition1.setMaterial( partitionEntityToMaterial( partition ) );
        partition1.setGasStation( partitionEntityToGasStation( partition ) );
        partition1.setPrice( partitionEntityToMoney( partition ) );
        partition1.setTransportation( partitionEntityToTransportation( partition ) );
        partition1.setId( partition.getId() );
        partition1.setAmount( partition.getAmount() );
        partition1.setCorrectedAmount( partition.getCorrectedAmount() );
        partition1.setNotes( partition.getNotes() );
        partition1.setExtraNotes( partition.getExtraNotes() );
        partition1.setCreatedAt( partition.getCreatedAt() );

        return partition1;
    }

    @Override
    public PartitionEntity domainToEntity(Partition partition) {
        if ( partition == null ) {
            return null;
        }

        PartitionEntity partitionEntity = new PartitionEntity();

        partitionEntity.setMaterialId( partitionMaterialId( partition ) );
        partitionEntity.setGasStationId( partitionGasStationId( partition ) );
        partitionEntity.setPriceCurrency( partitionPriceCurrency( partition ) );
        partitionEntity.setPriceAmount( partitionPriceAmount( partition ) );
        partitionEntity.setTransportationId( partitionTransportationId( partition ) );
        partitionEntity.setId( partition.getId() );
        partitionEntity.setAmount( partition.getAmount() );
        partitionEntity.setCorrectedAmount( partition.getCorrectedAmount() );
        partitionEntity.setNotes( partition.getNotes() );
        partitionEntity.setExtraNotes( partition.getExtraNotes() );
        partitionEntity.setCreatedAt( partition.getCreatedAt() );

        return partitionEntity;
    }

    protected Material partitionEntityToMaterial(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( partitionEntity.getMaterialId() );

        return material;
    }

    protected GasStation partitionEntityToGasStation(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        GasStation gasStation = new GasStation();

        gasStation.setId( partitionEntity.getGasStationId() );

        return gasStation;
    }

    protected Money partitionEntityToMoney(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( partitionEntity.getPriceCurrency() );
        money.setAmount( partitionEntity.getPriceAmount() );

        return money;
    }

    protected Transportation partitionEntityToTransportation(PartitionEntity partitionEntity) {
        if ( partitionEntity == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setId( partitionEntity.getTransportationId() );

        return transportation;
    }

    private Long partitionMaterialId(Partition partition) {
        if ( partition == null ) {
            return null;
        }
        Material material = partition.getMaterial();
        if ( material == null ) {
            return null;
        }
        Long id = material.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long partitionGasStationId(Partition partition) {
        if ( partition == null ) {
            return null;
        }
        GasStation gasStation = partition.getGasStation();
        if ( gasStation == null ) {
            return null;
        }
        Long id = gasStation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String partitionPriceCurrency(Partition partition) {
        if ( partition == null ) {
            return null;
        }
        Money price = partition.getPrice();
        if ( price == null ) {
            return null;
        }
        String currency = price.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double partitionPriceAmount(Partition partition) {
        if ( partition == null ) {
            return null;
        }
        Money price = partition.getPrice();
        if ( price == null ) {
            return null;
        }
        Double amount = price.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }

    private Long partitionTransportationId(Partition partition) {
        if ( partition == null ) {
            return null;
        }
        Transportation transportation = partition.getTransportation();
        if ( transportation == null ) {
            return null;
        }
        Long id = transportation.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
