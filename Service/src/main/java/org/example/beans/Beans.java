package org.example.beans;

import org.example.contract.repository.OfficeRepo;
import org.example.mappers.*;
import org.example.repositories.OfficeRepository;
import org.example.repositories.RefineryRepository;
import org.example.repositories.TransRepoJpa;
import org.example.repositories.VehicleRepository;
import org.example.repositories.adapters.OfficeAdapter;
import org.example.repositories.adapters.RefineryAdapter;
import org.example.repositories.adapters.TransAdapter;
import org.example.repositories.adapters.VehicleAdapter;
import org.example.useCases.CreateOffice;
import org.example.useCases.CreateRefinery;
import org.example.useCases.CreateTrans;
import org.example.useCases.CreateVehicle;
import org.example.validators.CreateOfficeValidator;
import org.example.validators.CreateRefineryValidator;
import org.example.validators.CreateTransValidator;
import org.example.validators.CreateVehicleValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public CreateTrans createTrans(TransRepoJpa transRepoJpa, CreateTransValidator createTransValidator){
        return new CreateTrans(createTransValidator,
                new DomainTransMapperImpl(),
                new TransAdapter(transRepoJpa, new TransMapperImpl()));
    }

    @Bean
    public CreateTransValidator createTransValidator(){
        return new CreateTransValidator();
    }

    @Bean
    public CreateRefinery createRefinery(RefineryRepository repository){
        return new CreateRefinery(new CreateRefineryValidator(),
                new RefineryDomainMapperImpl(),
                new RefineryAdapter(repository, new RefineryMapperImpl()));
    }

    @Bean
    public CreateVehicle createVehicle(VehicleRepository repository){
        return new CreateVehicle(new CreateVehicleValidator(), new VehicleDomainMapperImpl(),
                new VehicleAdapter(repository, new VehicleMapperImpl()));
    }

    @Bean
    public CreateOffice createOffice(OfficeDomainMapper domainMapper, OfficeRepo officeRepo){
        return new CreateOffice(domainMapper, officeRepo, new CreateOfficeValidator());
    }

    @Bean
    OfficeDomainMapper officeDomainMapper(){
        return new OfficeDomainMapperImpl();
    }

    @Bean
    OfficeRepo officeRepo(OfficeRepository officeRepository){
        return new OfficeAdapter(officeRepository, new OfficeMapperImpl());
    }

}
