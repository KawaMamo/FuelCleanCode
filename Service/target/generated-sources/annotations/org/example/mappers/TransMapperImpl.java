package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationType;
import org.example.model.Document;
import org.example.model.Refinery;
import org.example.model.Transportation;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T01:12:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransMapperImpl implements TransMapper {

    @Override
    public TransportationEntity domainToEntity(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }

        TransportationEntity transportationEntity = new TransportationEntity();

        transportationEntity.setVehicleEntityId( transportationVehicleId( transportation ) );
        transportationEntity.setRefineryId( transportationRefineryId( transportation ) );
        transportationEntity.setDocumentId( transportationDocumentId( transportation ) );
        transportationEntity.setId( transportation.getId() );
        transportationEntity.setIsDivided( transportation.getIsDivided() );
        transportationEntity.setIsPriced( transportation.getIsPriced() );
        transportationEntity.setSize( transportation.getSize() );
        transportationEntity.setCreatedAt( transportation.getCreatedAt() );
        transportationEntity.setType( transportationTypeToTransportationType( transportation.getType() ) );
        transportationEntity.setDeletedAt( transportation.getDeletedAt() );

        return transportationEntity;
    }

    @Override
    public Transportation entityToDomain(TransportationEntity transportationEntity) {
        if ( transportationEntity == null ) {
            return null;
        }

        Transportation transportation = new Transportation();

        transportation.setVehicle( transportationEntityToVehicle( transportationEntity ) );
        transportation.setRefinery( transportationEntityToRefinery( transportationEntity ) );
        transportation.setDocument( transportationEntityToDocument( transportationEntity ) );
        transportation.setId( transportationEntity.getId() );
        transportation.setIsDivided( transportationEntity.getIsDivided() );
        transportation.setIsPriced( transportationEntity.getIsPriced() );
        transportation.setSize( transportationEntity.getSize() );
        transportation.setCreatedAt( transportationEntity.getCreatedAt() );
        transportation.setType( transportationTypeToTransportationType1( transportationEntity.getType() ) );
        transportation.setDeletedAt( transportationEntity.getDeletedAt() );

        return transportation;
    }

    private Long transportationVehicleId(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }
        Vehicle vehicle = transportation.getVehicle();
        if ( vehicle == null ) {
            return null;
        }
        Long id = vehicle.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long transportationRefineryId(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }
        Refinery refinery = transportation.getRefinery();
        if ( refinery == null ) {
            return null;
        }
        Long id = refinery.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long transportationDocumentId(Transportation transportation) {
        if ( transportation == null ) {
            return null;
        }
        Document document = transportation.getDocument();
        if ( document == null ) {
            return null;
        }
        Long id = document.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected TransportationType transportationTypeToTransportationType(org.example.model.TransportationType transportationType) {
        if ( transportationType == null ) {
            return null;
        }

        TransportationType transportationType1;

        switch ( transportationType ) {
            case NORMAL: transportationType1 = TransportationType.NORMAL;
            break;
            case COMMERCIAL: transportationType1 = TransportationType.COMMERCIAL;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + transportationType );
        }

        return transportationType1;
    }

    protected Vehicle transportationEntityToVehicle(TransportationEntity transportationEntity) {
        if ( transportationEntity == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( transportationEntity.getVehicleEntityId() );

        return vehicle;
    }

    protected Refinery transportationEntityToRefinery(TransportationEntity transportationEntity) {
        if ( transportationEntity == null ) {
            return null;
        }

        Refinery refinery = new Refinery();

        refinery.setId( transportationEntity.getRefineryId() );

        return refinery;
    }

    protected Document transportationEntityToDocument(TransportationEntity transportationEntity) {
        if ( transportationEntity == null ) {
            return null;
        }

        Document document = new Document();

        document.setId( transportationEntity.getDocumentId() );

        return document;
    }

    protected org.example.model.TransportationType transportationTypeToTransportationType1(TransportationType transportationType) {
        if ( transportationType == null ) {
            return null;
        }

        org.example.model.TransportationType transportationType1;

        switch ( transportationType ) {
            case NORMAL: transportationType1 = org.example.model.TransportationType.NORMAL;
            break;
            case COMMERCIAL: transportationType1 = org.example.model.TransportationType.COMMERCIAL;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + transportationType );
        }

        return transportationType1;
    }
}
