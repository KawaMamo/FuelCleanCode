package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.OfficeEntity;
import org.example.entities.PersonEntity;
import org.example.entities.TrafficCenterEntity;
import org.example.entities.TransLogEntity;
import org.example.entities.VehicleEntity;
import org.example.model.Money;
import org.example.model.Office;
import org.example.model.Person;
import org.example.model.TrafficCenter;
import org.example.model.TransLog;
import org.example.model.Vehicle;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-28T17:01:29+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class TransLogMapperImpl implements TransLogMapper {

    private final TransLineMapper transLineMapper = Mappers.getMapper( TransLineMapper.class );

    @Override
    public TransLogEntity domainToEntity(TransLog transLog) {
        if ( transLog == null ) {
            return null;
        }

        TransLogEntity transLogEntity = new TransLogEntity();

        transLogEntity.setFeesCurrency( transLogFeesCurrency( transLog ) );
        transLogEntity.setFeesAmount( transLogFeesAmount( transLog ) );
        transLogEntity.setTransportation( TransLogMapper.transDomainToEntity( transLog.getTransportation() ) );
        transLogEntity.setId( transLog.getId() );
        transLogEntity.setVehicle( vehicleToVehicleEntity( transLog.getVehicle() ) );
        transLogEntity.setTransLine( transLineMapper.domainToEntity( transLog.getTransLine() ) );
        transLogEntity.setNotes( transLog.getNotes() );
        transLogEntity.setCreatedAt( transLog.getCreatedAt() );
        transLogEntity.setUpdatedAt( transLog.getUpdatedAt() );

        return transLogEntity;
    }

    @Override
    public TransLog entityToDomain(TransLogEntity transLog) {
        if ( transLog == null ) {
            return null;
        }

        TransLog transLog1 = new TransLog();

        transLog1.setFees( transLogEntityToMoney( transLog ) );
        transLog1.setTransportation( TransLogMapper.transEntityToDomain( transLog.getTransportation() ) );
        transLog1.setId( transLog.getId() );
        transLog1.setVehicle( vehicleEntityToVehicle( transLog.getVehicle() ) );
        transLog1.setTransLine( transLineMapper.entityToDomain( transLog.getTransLine() ) );
        transLog1.setNotes( transLog.getNotes() );
        transLog1.setCreatedAt( transLog.getCreatedAt() );
        transLog1.setUpdatedAt( transLog.getUpdatedAt() );

        return transLog1;
    }

    private String transLogFeesCurrency(TransLog transLog) {
        if ( transLog == null ) {
            return null;
        }
        Money fees = transLog.getFees();
        if ( fees == null ) {
            return null;
        }
        String currency = fees.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double transLogFeesAmount(TransLog transLog) {
        if ( transLog == null ) {
            return null;
        }
        Money fees = transLog.getFees();
        if ( fees == null ) {
            return null;
        }
        Double amount = fees.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }

    protected TrafficCenterEntity trafficCenterToTrafficCenterEntity(TrafficCenter trafficCenter) {
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

    protected OfficeEntity officeToOfficeEntity(Office office) {
        if ( office == null ) {
            return null;
        }

        OfficeEntity officeEntity = new OfficeEntity();

        officeEntity.setId( office.getId() );
        officeEntity.setName( office.getName() );
        officeEntity.setCreatedAt( office.getCreatedAt() );
        officeEntity.setUpdateAt( office.getUpdateAt() );

        return officeEntity;
    }

    protected PersonEntity personToPersonEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setId( person.getId() );
        personEntity.setName( person.getName() );
        personEntity.setFather( person.getFather() );
        personEntity.setMother( person.getMother() );
        personEntity.setNationalId( person.getNationalId() );
        personEntity.setBirthPlace( person.getBirthPlace() );
        personEntity.setBirthDate( person.getBirthDate() );
        personEntity.setCreatedAt( person.getCreatedAt() );
        personEntity.setUpdatedAt( person.getUpdatedAt() );

        return personEntity;
    }

    protected VehicleEntity vehicleToVehicleEntity(Vehicle vehicle) {
        if ( vehicle == null ) {
            return null;
        }

        VehicleEntity vehicleEntity = new VehicleEntity();

        vehicleEntity.setId( vehicle.getId() );
        vehicleEntity.setTurn( vehicle.getTurn() );
        vehicleEntity.setPlateNumber( vehicle.getPlateNumber() );
        vehicleEntity.setTrafficCenter( trafficCenterToTrafficCenterEntity( vehicle.getTrafficCenter() ) );
        vehicleEntity.setSize( vehicle.getSize() );
        vehicleEntity.setOffice( officeToOfficeEntity( vehicle.getOffice() ) );
        vehicleEntity.setDriver( personToPersonEntity( vehicle.getDriver() ) );
        vehicleEntity.setCreatedAt( vehicle.getCreatedAt() );
        vehicleEntity.setUpdatedAt( vehicle.getUpdatedAt() );

        return vehicleEntity;
    }

    protected Money transLogEntityToMoney(TransLogEntity transLogEntity) {
        if ( transLogEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( transLogEntity.getFeesCurrency() );
        money.setAmount( transLogEntity.getFeesAmount() );

        return money;
    }

    protected TrafficCenter trafficCenterEntityToTrafficCenter(TrafficCenterEntity trafficCenterEntity) {
        if ( trafficCenterEntity == null ) {
            return null;
        }

        TrafficCenter trafficCenter = new TrafficCenter();

        trafficCenter.setId( trafficCenterEntity.getId() );
        trafficCenter.setName( trafficCenterEntity.getName() );
        trafficCenter.setCreatedAt( trafficCenterEntity.getCreatedAt() );
        trafficCenter.setUpdatedAt( trafficCenterEntity.getUpdatedAt() );

        return trafficCenter;
    }

    protected Office officeEntityToOffice(OfficeEntity officeEntity) {
        if ( officeEntity == null ) {
            return null;
        }

        Office office = new Office();

        office.setId( officeEntity.getId() );
        office.setName( officeEntity.getName() );
        office.setCreatedAt( officeEntity.getCreatedAt() );
        office.setUpdateAt( officeEntity.getUpdateAt() );

        return office;
    }

    protected Person personEntityToPerson(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        Person person = new Person();

        person.setId( personEntity.getId() );
        person.setName( personEntity.getName() );
        person.setFather( personEntity.getFather() );
        person.setMother( personEntity.getMother() );
        person.setNationalId( personEntity.getNationalId() );
        person.setBirthPlace( personEntity.getBirthPlace() );
        person.setBirthDate( personEntity.getBirthDate() );
        person.setCreatedAt( personEntity.getCreatedAt() );
        person.setUpdatedAt( personEntity.getUpdatedAt() );

        return person;
    }

    protected Vehicle vehicleEntityToVehicle(VehicleEntity vehicleEntity) {
        if ( vehicleEntity == null ) {
            return null;
        }

        Vehicle vehicle = new Vehicle();

        vehicle.setId( vehicleEntity.getId() );
        vehicle.setTurn( vehicleEntity.getTurn() );
        vehicle.setPlateNumber( vehicleEntity.getPlateNumber() );
        vehicle.setTrafficCenter( trafficCenterEntityToTrafficCenter( vehicleEntity.getTrafficCenter() ) );
        vehicle.setSize( vehicleEntity.getSize() );
        vehicle.setOffice( officeEntityToOffice( vehicleEntity.getOffice() ) );
        vehicle.setDriver( personEntityToPerson( vehicleEntity.getDriver() ) );
        vehicle.setCreatedAt( vehicleEntity.getCreatedAt() );
        vehicle.setUpdatedAt( vehicleEntity.getUpdatedAt() );

        return vehicle;
    }
}
