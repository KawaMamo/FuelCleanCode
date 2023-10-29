package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateVehicleRequest;
import org.example.contract.request.update.UpdateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.TrafficCenter;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-29T16:02:01+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class VehicleDomainMapperImpl implements VehicleDomainMapper {

    @Override
    public Vehicle requestToDomain(CreateVehicleRequest request) {
        if ( request == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setTrafficCenter( createVehicleRequestToTrafficCenter( request ) );
        vehicle.setOffice( createVehicleRequestToOffice( request ) );
        vehicle.setDriver( createVehicleRequestToPerson( request ) );
        vehicle.setTurn( request.getTurn() );
        vehicle.setPlateNumber( request.getPlateNumber() );
        vehicle.setSize( request.getSize() );

        return vehicle;
    }

    @Override
    public VehicleResponse domainToResponse(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleResponse vehicleResponse = new VehicleResponse();

        vehicleResponse.setId( vehicle.getId() );
        vehicleResponse.setTurn( vehicle.getTurn() );
        vehicleResponse.setPlateNumber( vehicle.getPlateNumber() );
        vehicleResponse.setTrafficCenter( vehicle.getTrafficCenter() );
        vehicleResponse.setSize( vehicle.getSize() );
        vehicleResponse.setOffice( vehicle.getOffice() );
        vehicleResponse.setDriver( vehicle.getDriver() );
        vehicleResponse.setCreatedAt( vehicle.getCreatedAt() );
        vehicleResponse.setUpdatedAt( vehicle.getUpdatedAt() );

        return vehicleResponse;
    }

    @Override
    public Vehicle requestToDomain(UpdateVehicleRequest request) {
        if ( request == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setTrafficCenter( updateVehicleRequestToTrafficCenter( request ) );
        vehicle.setOffice( updateVehicleRequestToOffice( request ) );
        vehicle.setDriver( updateVehicleRequestToPerson( request ) );
        vehicle.setId( request.getId() );
        vehicle.setTurn( request.getTurn() );
        vehicle.setPlateNumber( request.getPlateNumber() );
        vehicle.setSize( request.getSize() );

        return vehicle;
    }

    protected TrafficCenter createVehicleRequestToTrafficCenter(CreateVehicleRequest createVehicleRequest) {
        if ( createVehicleRequest == null ) {
            return null;
        }

        TrafficCenter trafficCenter = new TrafficCenter();

        trafficCenter.setId( createVehicleRequest.getTrafficCenter_id() );

        return trafficCenter;
    }

    protected Office createVehicleRequestToOffice(CreateVehicleRequest createVehicleRequest) {
        if ( createVehicleRequest == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( createVehicleRequest.getOffice_id() );

        return office;
    }

    protected Person createVehicleRequestToPerson(CreateVehicleRequest createVehicleRequest) {
        if ( createVehicleRequest == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( createVehicleRequest.getDriver_id() );

        return person;
    }

    protected TrafficCenter updateVehicleRequestToTrafficCenter(UpdateVehicleRequest updateVehicleRequest) {
        if ( updateVehicleRequest == null ) {
            return null;
        }

        TrafficCenter trafficCenter = new TrafficCenter();

        trafficCenter.setId( updateVehicleRequest.getTrafficCenter_id() );

        return trafficCenter;
    }

    protected Office updateVehicleRequestToOffice(UpdateVehicleRequest updateVehicleRequest) {
        if ( updateVehicleRequest == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( updateVehicleRequest.getOffice_id() );

        return office;
    }

    protected Person updateVehicleRequestToPerson(UpdateVehicleRequest updateVehicleRequest) {
        if ( updateVehicleRequest == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( updateVehicleRequest.getDriver_id() );

        return person;
    }
}
