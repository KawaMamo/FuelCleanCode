package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateVehicleRequest;
import org.example.contract.response.VehicleResponse;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.TrafficCenter;
import org.example.model.Vehicle;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-18T20:15:10+0300",
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
        vehicleResponse.setPlateNumber( vehicle.getPlateNumber() );
        vehicleResponse.setTrafficCenter( vehicle.getTrafficCenter() );
        vehicleResponse.setSize( vehicle.getSize() );
        vehicleResponse.setOffice( vehicle.getOffice() );
        vehicleResponse.setDriver( vehicle.getDriver() );
        vehicleResponse.setCreatedAt( vehicle.getCreatedAt() );

        return vehicleResponse;
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
}
