package org.example.beans;

import org.example.adapters.*;
import org.example.contract.repository.*;
import org.example.mappers.*;
import org.example.repositories.*;
import org.example.useCases.*;
import org.example.validators.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public CreateTrans createTrans(TransRepoJpa transRepoJpa,
                                   CreateTransValidator createTransValidator,
                                   VehicleRepo vehicleRepo,
                                   RefineryRepo refineryRepo){
        return new CreateTrans(createTransValidator,
                new DomainTransMapperImpl(),
                new TransAdapter(transRepoJpa, new TransMapperImpl()), vehicleRepo, refineryRepo);
    }

    @Bean
    VehicleMapper vehicleMapper(){
        return new VehicleMapperImpl();
    }

    @Bean
    VehicleRepo vehicleRepo(VehicleRepository repository, VehicleMapper mapper){
        return new VehicleAdapter(repository, mapper);
    }

    @Bean
    RefineryRepo refineryRepo(RefineryRepository repository, RefineryMapper mapper){
        return new RefineryAdapter(repository, mapper);
    }

    @Bean
    RefineryMapper refineryMapper(){
        return new RefineryMapperImpl();
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
    public CreateVehicle createVehicle(VehicleRepository repository,
                                       OfficeRepo officeRepo,
                                       PersonRepo personRepo,
                                       TrafficCenterRepo trafficCenterRepo, VehicleRepo vehicleRepo){
        return new CreateVehicle(new CreateVehicleValidator(personRepo, officeRepo, trafficCenterRepo), new VehicleDomainMapperImpl(),
                vehicleRepo, trafficCenterRepo, officeRepo, personRepo);
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
    CreateCategory category(CategoryRepository categoryRepository,
                            PriceCategoryAdapter priceCategoryAdapter,
                            MaterialRepo materialRepo,
                            CategoryMapper categoryMapper){
        return new CreateCategory(new CreateCategoryValidator(priceCategoryAdapter),
                new CategoryDomainMapperImpl(),
                new CategoryAdapter(categoryRepository, categoryMapper), priceCategoryAdapter, materialRepo);
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
    CreateMaterial createMaterial(MaterialRepository repository, MaterialMapper materialMapper){
        return new CreateMaterial(new CreateMaterialValidator(),
                new MaterialAdapter(repository, materialMapper),
                new MaterialDomainMapperImpl());
    }

    @Bean
    MaterialRepo materialRepo(MaterialRepository repository, MaterialMapper mapper){
        return new MaterialAdapter(repository, mapper);
    }

    @Bean
    MaterialMapper materialMapper(){
        return new MaterialMapperImpl();
    }

    @Bean
    CategoryMapper categoryMapper(){return new CategoryMapperImpl();}

    @Bean
    CreateTrafficCenter createTrafficCenter(CreateTrafficCenterValidator createTrafficCenterValidator
            , TrafficCenterDomainMapper mapper, TrafficCenterRepo trafficCenterRepo){
        return new CreateTrafficCenter(createTrafficCenterValidator, mapper, trafficCenterRepo);
    }

    @Bean
    CreateTrafficCenterValidator createTrafficCenterValidator(){
        return new CreateTrafficCenterValidator();
    }

    @Bean
    TrafficCenterDomainMapper trafficCenterDomainMapper(){
        return new TrafficCenterDomainMapperImpl();
    }

    @Bean
    TrafficCenterRepo trafficCenterRepo(TrafficCenterRepository repository, TrafficCenterMapper trafficCenterMapper){
        return new TrafficCenterAdapter(repository, trafficCenterMapper);
    }

    @Bean
    TrafficCenterMapper trafficCenterMapper(){
        return new TrafficCenterMapperImpl();
    }

    @Bean
    CreatePerson createPerson(PersonRepo personRepo){
        return new CreatePerson(personRepo, new PersonDomainMapperImpl(), new CreatePersonValidator());
    }

    @Bean
    PersonRepo personRepo(PersonRepository repository){
        return new PersonAdapter(repository, new PersonMapperImpl());
    }
    @Bean
    CreateRegion createRegion(RegionRepository repository){
        return new CreateRegion(new CreateRegionValidator(),
                new RegionDomainMapperImpl(),
                new RegionAdapter(repository, new RegionMapperImpl()));
    }

    @Bean
    CreateGroup createGroup(GroupRepository repository){
        return new CreateGroup(new CreateGroupValidator(),
                new GroupDomainMapperImpl(),
                new GroupAdapter(repository, new GroupMapperImpl()));
    }

    @Bean
    CreateGasStation createGasStation(CreateGasStationValidator createGasStationValidator,
                                      GasStationDomainMapper gasStationDomainMapper,
                                      GasStationRepo gasStationRepo,
                                      PriceCategoryRepo priceCategoryRepo,
                                      RegionRepo regionRepo,
                                      PersonRepo personRepo,
                                      GroupRepo groupRepo){
        return new CreateGasStation(createGasStationValidator,
                gasStationDomainMapper,
                gasStationRepo, priceCategoryRepo, regionRepo, personRepo, groupRepo);
    }

    @Bean
    CreateGasStationValidator createGasStationValidator(PriceCategoryRepo priceCategoryRepo,
                                                        RegionRepo regionRepo,
                                                        GroupRepo groupRepo,
                                                        PersonRepo personRepo){
        return new CreateGasStationValidator(priceCategoryRepo,
                regionRepo,
                groupRepo,
                personRepo);
    }

    @Bean
    GasStationDomainMapper gasStationDomainMapper(){
        return new GasStationDomainMapperImpl();
    }

    @Bean
    GasStationRepo gasStationRepo(GasStationRepository gasStationRepository){
        return new GasStationAdapter(gasStationRepository, new GasStationMapperImpl());
    }

    @Bean
    RegionRepo regionRepo(RegionRepository regionRepository){
        return new RegionAdapter(regionRepository, new RegionMapperImpl());
    }

    @Bean
    GroupRepo groupRepo(GroupRepository groupRepository){
        return new GroupAdapter(groupRepository, new GroupMapperImpl());
    }

    @Bean
    CreatePartition createPartition(PartitionDomainMapper partitionDomainMapper, PartitionRepo partitionRepo, TransRepo transRepo){
        return new CreatePartition(new CreatePartitionValidator(), partitionDomainMapper, partitionRepo, transRepo);
    }

    @Bean
    PartitionDomainMapper partitionDomainMapper(){
        return new PartitionDomainMapperImpl();
    }

    @Bean
    PartitionRepo partitionRepo(PartitionRepository repository){
        return new PartitionAdapter(repository, new PartitionMapperImpl());
    }

    @Bean
    TransRepo transRepo(TransRepoJpa transRepoJpa, TransMapper transMapper){
        return new TransAdapter(transRepoJpa, transMapper);
    }

    @Bean
    TransMapper transMapper(){
        return new TransMapperImpl();
    }

}
