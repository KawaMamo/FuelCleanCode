package org.example.beans;

import org.example.contract.repository.OfficeRepo;
import org.example.mappers.*;
import org.example.repositories.*;
import org.example.repositories.adapters.*;
import org.example.useCases.*;
import org.example.validators.*;
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

    @Bean
    CreateCategory category(CategoryRepository categoryRepository, PriceCategoryAdapter priceCategoryAdapter){
        return new CreateCategory(new CreateCategoryValidator(priceCategoryAdapter),
                new CategoryDomainMapperImpl(),
                new CategoryAdapter(categoryRepository, new CategoryMapperImpl()));
    }

    @Bean
    CreatePriceCategory createPriceCategory(PriceCategoryRepository priceCategoryRepository, PriceCategoryAdapter priceCategoryAdapter){
        return new CreatePriceCategory(new CreatePriceCategoryValidator(),
                new PriceCategoryDomainMapperImpl(),
                priceCategoryAdapter);
    }

    @Bean
    PriceCategoryAdapter priceCategoryAdapter(PriceCategoryRepository priceCategoryRepository){
        return new PriceCategoryAdapter(priceCategoryRepository, new PriceCategoryMapperImpl());
    }

    @Bean
    CreateMaterial createMaterial(MaterialRepository repository){
        return new CreateMaterial(new CreateMaterialValidator(),
                new MaterialAdapter(repository, new MaterialMapperImpl()),
                new MaterialDomainMapperImpl());
    }

}
