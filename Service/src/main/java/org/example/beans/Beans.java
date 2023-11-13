package org.example.beans;

import org.example.adapters.*;
import org.example.contract.repository.*;
import org.example.mappers.*;
import org.example.repositories.*;
import org.example.security.EndPoints;
import org.example.security.TokenService;
import org.example.useCases.create.*;
import org.example.useCases.delete.*;
import org.example.useCases.update.*;
import org.example.validators.create.*;
import org.example.validators.update.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public CreateTrans createTrans(TransRepoJpa transRepoJpa,
                                   CreateTransValidator createTransValidator,
                                   VehicleRepo vehicleRepo,
                                   RefineryRepo refineryRepo, DomainTransMapper domainTransMapper){
        return new CreateTrans(createTransValidator,
                domainTransMapper,
                new TransAdapter(transRepoJpa, new TransMapperImpl()), vehicleRepo, refineryRepo);
    }

    @Bean
    DomainTransMapper domainTransMapper(){
        return new DomainTransMapperImpl();
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
    public CreateTransValidator createTransValidator(RefineryRepo refineryRepo, VehicleRepo vehicleRepo){
        return new CreateTransValidator(refineryRepo, vehicleRepo);
    }

    @Bean
    public CreateRefinery createRefinery(RefineryRepository repository, RefineryDomainMapper refineryDomainMapper){
        return new CreateRefinery(new CreateRefineryValidator(),
                refineryDomainMapper,
                new RefineryAdapter(repository, new RefineryMapperImpl()));
    }

    @Bean
    RefineryDomainMapper refineryDomainMapper(){
        return new RefineryDomainMapperImpl();
    }

    @Bean
    public CreateVehicle createVehicle(VehicleRepository repository,
                                       OfficeRepo officeRepo,
                                       PersonRepo personRepo,
                                       TrafficCenterRepo trafficCenterRepo, VehicleRepo vehicleRepo, VehicleDomainMapper vehicleDomainMapper){
        return new CreateVehicle(new CreateVehicleValidator(personRepo, officeRepo, trafficCenterRepo), vehicleDomainMapper,
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
    OfficeRepo officeRepo(OfficeRepository officeRepository, OfficeMapper officeMapper){
        return new OfficeAdapter(officeRepository, officeMapper);
    }

    @Bean
    OfficeMapper officeMapper(){
        return new OfficeMapperImpl();
    }

    @Bean
    CreateCategory createCategory(CategoryRepository categoryRepository,
                                  PriceCategoryAdapter priceCategoryAdapter,
                                  MaterialRepo materialRepo,
                                  CategoryMapper categoryMapper){
        return new CreateCategory(new CreateCategoryValidator(priceCategoryAdapter),
                new CategoryDomainMapperImpl(),
                new CategoryAdapter(categoryRepository, categoryMapper), priceCategoryAdapter, materialRepo);
    }

    @Bean
    CreatePriceCategory createPriceCategory(PriceCategoryRepository priceCategoryRepository,
                                            PriceCategoryAdapter priceCategoryAdapter, PriceCategoryDomainMapper mapper){
        return new CreatePriceCategory(new CreatePriceCategoryValidator(),
                mapper,
                priceCategoryAdapter);
    }

    @Bean
    PriceCategoryAdapter priceCategoryAdapter(PriceCategoryRepository priceCategoryRepository, PriceCategoryMapper mapper){
        return new PriceCategoryAdapter(priceCategoryRepository, mapper);
    }

    @Bean
    PlaceMapper placeMapper(){
        return new PlaceMapperImpl();
    }

    @Bean
    PriceCategoryMapper priceCategoryMapper(){
        return new PriceCategoryMapperImpl();
    }

    @Bean
    CreateMaterial createMaterial(MaterialRepository repository, MaterialMapper materialMapper, MaterialDomainMapper materialDomainMapper){
        return new CreateMaterial(new CreateMaterialValidator(),
                new MaterialAdapter(repository, materialMapper),
                materialDomainMapper);
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
    CreatePerson createPerson(PersonRepo personRepo, PersonDomainMapper personDomainMapper){
        return new CreatePerson(personRepo, personDomainMapper, new CreatePersonValidator());
    }

    @Bean
    PersonDomainMapper personDomainMapper(){
        return new PersonDomainMapperImpl();
    }

    @Bean
    PersonRepo personRepo(PersonRepository repository, PersonMapper personMapper){
        return new PersonAdapter(repository, personMapper);
    }

    @Bean
    PersonMapper personMapper(){
        return new PersonMapperImpl();
    }
    @Bean
    CreateRegion createRegion(RegionRepository repository, RegionMapper regionMapper, RegionDomainMapper regionDomainMapper){
        return new CreateRegion(new CreateRegionValidator(),
                regionDomainMapper,
                new RegionAdapter(repository, regionMapper));
    }

    @Bean
    RegionMapper regionMapper(){
        return new RegionMapperImpl();
    }

    @Bean
    CreateGroup createGroup(GroupRepository repository, GroupDomainMapper groupDomainMapper){
        return new CreateGroup(new CreateGroupValidator(),
                groupDomainMapper,
                new GroupAdapter(repository, new GroupMapperImpl()));
    }

    @Bean
    CreateGasStation createGasStation(CreateGasStationValidator createGasStationValidator,
                                      GasStationDomainMapper gasStationDomainMapper,
                                      GasStationRepo gasStationRepo,
                                      PriceCategoryRepo priceCategoryRepo,
                                      RegionRepo regionRepo,
                                      PersonRepo personRepo,
                                      GroupRepo groupRepo, MaterialRepo materialRepo){
        return new CreateGasStation(createGasStationValidator,
                gasStationDomainMapper,
                gasStationRepo, priceCategoryRepo, regionRepo, personRepo, groupRepo, materialRepo);
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
    GasStationMapper gasStationMapper(){
        return new GasStationMapperImpl();
    }


    @Bean
    RegionRepo regionRepo(RegionRepository regionRepository){
        return new RegionAdapter(regionRepository, new RegionMapperImpl());
    }

    @Bean
    GroupRepo groupRepo(GroupRepository groupRepository, GroupMapper groupMapper){
        return new GroupAdapter(groupRepository, groupMapper);
    }

    @Bean
    GroupMapper groupMapper(){
        return new GroupMapperImpl();
    }

    @Bean
    CreatePartition createPartition(PartitionDomainMapper partitionDomainMapper,
                                    PartitionRepo partitionRepo,
                                    MaterialRepo materialRepo,
                                    GasStationRepo gasStationRepo,
                                    TransRepo transRepo){
        return new CreatePartition(new CreatePartitionValidator(materialRepo, gasStationRepo, transRepo), partitionDomainMapper, partitionRepo, transRepo, gasStationRepo, materialRepo);
    }

    @Bean
    PartitionDomainMapper partitionDomainMapper(){
        return new PartitionDomainMapperImpl();
    }

    @Bean
    PartitionRepo partitionRepo(PartitionRepository repository, PartitionMapper partitionMapper){
        return new PartitionAdapter(repository, partitionMapper);
    }

    @Bean
    PartitionMapper partitionMapper(){
        return new PartitionMapperImpl();
    }

    @Bean
    TransRepo transRepo(TransRepoJpa transRepoJpa, TransMapper transMapper){
        return new TransAdapter(transRepoJpa, transMapper);
    }

    @Bean
    TransMapper transMapper(){
        return new TransMapperImpl();
    }

    @Bean
    CreateForfeit createForfeit(ForfeitRepository repository, VehicleRepo vehicleRepo, PartitionRepo partitionRepo, ForfeitRepo forfeitRepo){
        return new CreateForfeit(forfeitRepo, new ForfeitDomainMapperImpl(), new CreateForfeitValidator(partitionRepo, vehicleRepo), vehicleRepo, partitionRepo);
    }

    @Bean
    ForfeitRepo forfeitRepo(ForfeitRepository repository, ForfeitMapper forfeitMapper){
        return new ForfeitAdapter(repository, forfeitMapper);
    }

    @Bean
    ForfeitMapper forfeitMapper(){
        return new ForfeitMapperImpl();
    }

    @Bean
    CreateTransLine createTransLine(TransLineRepo transLineRepo,
                                    TransLineDomainMapper transLineDomainMapper,
                                    CreateTransLineValidator validator,
                                    PlaceTypeDetector placeTypeDetector){
        return new CreateTransLine(transLineRepo, transLineDomainMapper, validator, placeTypeDetector);
    }

    @Bean
    PlaceTypeDetector placeTypeDetector(GasStationRepo gasStationRepo,
                                        RefineryRepo refineryRepo, RegionRepo regionRepo){
        return new PlaceTypeDetector(gasStationRepo, refineryRepo, regionRepo);
    }

    @Bean
    TransLineRepo transLineRepo(TransLineRepository repository, TransLineMapper transLineMapper){
        return new TransLineAdapter(repository, transLineMapper);
    }

    @Bean
    TransLineDomainMapper transLineDomainMapper(){
        return new TransLineDomainMapperImpl();
    }

    @Bean
    TransLineMapper transLineMapper(){
        return new TransLineMapperImpl();
    }

    @Bean
    CreateTransLineValidator createTransLineValidator(GasStationRepo gasStationRepo, RefineryRepo refineryRepo, RegionRepo regionRepo){
        return new CreateTransLineValidator(gasStationRepo, refineryRepo, regionRepo);
    }

    @Bean
    CreateTransLog createTransLog(TransLogRepo transLogRepo,
                                  TransLineRepo transLineRepo,
                                  VehicleRepo vehicleRepo,
                                  TransRepo transRepo, TransLogDomainMapper transLogDomainMapper){
        return new CreateTransLog(transLogRepo,
                transLogDomainMapper,
                new CreateTransLogValidator(transLineRepo,
                        vehicleRepo,
                        transRepo), vehicleRepo, transLineRepo, transRepo);
    }

    @Bean
    TransLogRepo transLogRepo(TransLogRepository transLogRepository, TransLogMapper transLogMapper){
        return new TransLogAdapter(transLogRepository, transLogMapper);
    }

    @Bean
    TransLogMapper transLogMapper(){
        return new TransLogMapperImpl();
    }

    @Bean
    CreateTransferMaterial createTransferMaterial(CreateTransferMaterialValidator createTransferMaterialValidator,
                                                  TransferMaterialRepo transferMaterialRepo,
                                                  GasStationRepo gasStationRepo,
                                                  MaterialRepo materialRepo){
        return new CreateTransferMaterial(createTransferMaterialValidator,
                new TransferMaterialDomainMapperImpl(),
                transferMaterialRepo, gasStationRepo, materialRepo);
    }

    @Bean
    CreateTransferMaterialValidator createTransferMaterialValidator(MaterialRepo materialRepo, GasStationRepo gasStationRepo){
        return new CreateTransferMaterialValidator(materialRepo, gasStationRepo);
    }

    @Bean
    TransferMaterialRepo transferMaterialRepo(TransferMaterialRepository transferMaterialRepository, TransferMaterialsMapper transferMaterialsMapper){
        return new TransferMaterialsAdapter(transferMaterialRepository, transferMaterialsMapper);
    }

    @Bean
    TransferMaterialsMapper transferMaterialsMapper(){
        return new TransferMaterialsMapperImpl();
    }

    @Bean
    UpdateCategory updateCategory(UpdateCategoryValidator updateCategoryValidator,
                                  CategoryDomainMapper mapper,
                                  CategoryRepo categoryRepo,
                                  PriceCategoryRepo priceCategoryRepo,
                                  MaterialRepo materialRepo){
        return new UpdateCategory(updateCategoryValidator, mapper, categoryRepo, priceCategoryRepo, materialRepo);
    }

    @Bean
    UpdateCategoryValidator updateCategoryValidator(PriceCategoryRepo priceCategoryRepo, MaterialRepo materialRepo){
        return new UpdateCategoryValidator(priceCategoryRepo, materialRepo);
    }

    @Bean
    CategoryDomainMapper categoryDomainMapper(){
        return new CategoryDomainMapperImpl();
    }

    @Bean
    CategoryRepo categoryRepo(CategoryRepository categoryRepository, CategoryMapper categoryMapper){
        return new CategoryAdapter(categoryRepository, categoryMapper);
    }

    @Bean
    UpdateDocument updateDocument(DocumentRepo documentRepo, DocumentDomainMapper documentDomainMapper, UpdateDocumentValidator validator){
        return new UpdateDocument(documentRepo, documentDomainMapper, validator);
    }

    @Bean
    DocumentRepo documentRepo(DocumentRepository repository, DocumentMapper mapper){
        return new DocumentAdapter(repository, mapper);
    }

    @Bean
    DocumentMapper documentMapper(){
        return new DocumentMapperImpl();
    }

    @Bean
    DocumentDomainMapper documentDomainMapper(){
        return new DocumentDomainMapperImpl();
    }

    @Bean
    UpdateDocumentValidator updateDocumentValidator(){
        return new UpdateDocumentValidator();
    }

    @Bean
    CreateDocument createDocument(CreateDocumentValidator validator, DocumentDomainMapper mapper, DocumentRepo documentRepo){
        return new CreateDocument(validator, mapper, documentRepo);
    }

    @Bean
    CreateDocumentValidator createDocumentValidator(){
        return new CreateDocumentValidator();
    }

    @Bean
    UpdateForfeit updateForfeit(UpdateForfeitValidator validator, ForfeitDomainMapper mapper, ForfeitRepo forfeitRepo){
        return new UpdateForfeit(validator, mapper, forfeitRepo);
    }

    @Bean
    UpdateForfeitValidator updateForfeitValidator(PartitionRepo partitionRepo, VehicleRepo vehicleRepo){
        return new UpdateForfeitValidator(partitionRepo, vehicleRepo);
    }

    @Bean
    ForfeitDomainMapper forfeitDomainMapper(){
        return new ForfeitDomainMapperImpl();
    }

    @Bean
    UpdateGasStation updateGasStation(UpdateGasStationValidator validator, GasStationDomainMapper gasStationDomainMapper, GasStationRepo gasStationRepo){
        return new UpdateGasStation(validator, gasStationDomainMapper, gasStationRepo);
    }

    @Bean
    UpdateGasStationValidator updateGasStationValidator(PriceCategoryRepo priceCategoryRepo,
                                                        RegionRepo regionRepo,
                                                        GroupRepo groupRepo,
                                                        PersonRepo personRepo){
        return new UpdateGasStationValidator(priceCategoryRepo, regionRepo, groupRepo, personRepo);
    }

    @Bean
    UpdateGroup updateGroup(UpdateGroupValidator validator, GroupDomainMapper mapper, GroupRepo groupRepo){
        return new UpdateGroup(validator, mapper, groupRepo);
    }

    @Bean
    UpdateGroupValidator updateGroupValidator(){
        return new UpdateGroupValidator();
    }

    @Bean
    GroupDomainMapper groupDomainMapper(){
        return new GroupDomainMapperImpl();
    }

    @Bean
    UpdateMaterial updateMaterial(UpdateMaterialValidator updateMaterialValidator, MaterialDomainMapper mapper, MaterialRepo materialRepo){
        return new UpdateMaterial(updateMaterialValidator, mapper, materialRepo);
    }

    @Bean
    MaterialDomainMapper materialDomainMapper(){
        return new MaterialDomainMapperImpl();
    }

    @Bean
    UpdateMaterialValidator updateMaterialValidator(){
        return new UpdateMaterialValidator();
    }

    @Bean
    UpdateOffice updateOffice(UpdateOfficeValidator validator, OfficeDomainMapper mapper, OfficeRepo officeRepo){
        return new UpdateOffice(validator, mapper, officeRepo);
    }

    @Bean
    UpdateOfficeValidator updateOfficeValidator(){
        return new UpdateOfficeValidator();
    }

    @Bean
    UpdatePartition updatePartition(UpdatePartitionValidator validator, PartitionDomainMapper mapper, PartitionRepo partitionRepo){
        return new UpdatePartition(validator, mapper, partitionRepo);
    }

    @Bean
    UpdatePartitionValidator updatePartitionValidator(MaterialRepo materialRepo, GasStationRepo gasStationRepo, TransRepo transRepo){
        return new UpdatePartitionValidator(materialRepo, gasStationRepo, transRepo);
    }

    @Bean
    UpdatePerson updatePerson(UpdatePersonValidator validator, PersonDomainMapper mapper, PersonRepo personRepo){
        return new UpdatePerson(validator, mapper, personRepo);
    }

    @Bean
    UpdatePersonValidator updatePersonValidator(){
        return new UpdatePersonValidator();
    }

    @Bean
    UpdatePriceCategory updatePriceCategory(UpdatePriceCategoryValidator validator, PriceCategoryDomainMapper mapper, PriceCategoryRepo priceCategoryRepo){
        return new UpdatePriceCategory(validator, mapper, priceCategoryRepo);
    }
    @Bean
    UpdatePriceCategoryValidator updatePriceCategoryValidator(){
        return new UpdatePriceCategoryValidator();
    }
    @Bean
    UpdateRefinery updateRefinery(UpdateRefineryValidator validator, RefineryDomainMapper mapper, RefineryRepo refineryRepo){
        return new UpdateRefinery(validator, refineryRepo, mapper);
    }

    @Bean
    UpdateRefineryValidator updateRefineryValidator(){
        return new UpdateRefineryValidator();
    }

    @Bean
    PriceCategoryDomainMapper priceCategoryDomainMapper(){
        return new PriceCategoryDomainMapperImpl();
    }
    @Bean
    UpdateRegion updateRegion(UpdateRegionValidator validator, RegionDomainMapper mapper, RegionRepo regionRepo){
        return new UpdateRegion(validator, mapper, regionRepo);
    }
    @Bean
    UpdateRegionValidator updateRegionValidator(){
        return new UpdateRegionValidator();
    }

    @Bean
    RegionDomainMapper regionDomainMapper(){
        return new RegionDomainMapperImpl();
    }
    @Bean
    UpdateTrafficCenter updateTrafficCenter(UpdateTrafficCenterValidator validator, TrafficCenterDomainMapper mapper, TrafficCenterRepo repo){
        return new UpdateTrafficCenter(validator, mapper, repo);
    }
    @Bean
    UpdateTrafficCenterValidator updateTrafficCenterValidator(){
        return new UpdateTrafficCenterValidator();
    }
    @Bean
    UpdateTrans updateTrans(UpdateTransValidator validator, DomainTransMapper mapper, TransRepo repo){
        return new UpdateTrans(validator, mapper, repo);
    }
    @Bean
    UpdateTransValidator updateTransValidator(RefineryRepo refineryRepo, VehicleRepo vehicleRepo, TransRepo transRepo){
        return new UpdateTransValidator(refineryRepo, vehicleRepo, transRepo);
    }
    @Bean
    UpdateTransferMaterial updateTransferMaterial(UpdateTransferMaterialValidator validator,
                                                  TransferMaterialDomainMapper mapper,
                                                  TransferMaterialRepo repo){
        return new UpdateTransferMaterial(validator, repo, mapper);
    }

    @Bean
    UpdateTransferMaterialValidator updateTransferMaterialValidator(MaterialRepo materialRepo, GasStationRepo gasStationRepo){
        return new UpdateTransferMaterialValidator(materialRepo, gasStationRepo);
    }
    @Bean
    TransferMaterialDomainMapper transferMaterialDomainMapper(){
        return new TransferMaterialDomainMapperImpl();
    }
    @Bean
    UpdateTransLine updateTransLine(UpdateTransLineValidator validator, TransLineDomainMapper mapper, TransLineRepo transLineRepo, PlaceTypeDetector placeTypeDetector){
        return new UpdateTransLine(validator, mapper, transLineRepo, placeTypeDetector);
    }
    @Bean
    UpdateTransLineValidator updateTransLineValidator(GasStationRepo gasStationRepo, RefineryRepo refineryRepo, RegionRepo regionRepo){
        return new UpdateTransLineValidator(gasStationRepo, refineryRepo, regionRepo);
    }
    @Bean
    UpdateTransLog updateTransLog(UpdateTransLogValidator validator, TransLogDomainMapper mapper, TransLogRepo transLogRepo){
        return new UpdateTransLog(validator, transLogRepo, mapper);
    }
    @Bean
    UpdateTransLogValidator updateTransLogValidator(TransLineRepo transLineRepo, VehicleRepo vehicleRepo, TransRepo transRepo){
        return new UpdateTransLogValidator(transLineRepo, vehicleRepo, transRepo);
    }

    @Bean
    TransLogDomainMapper transLogDomainMapper(){
        return new TransLogDomainMapperImpl();
    }
    @Bean
    UpdateVehicle updateVehicle(UpdateVehicleValidator validator, VehicleDomainMapper mapper, VehicleRepo vehicleRepo){
        return new UpdateVehicle(validator, vehicleRepo, mapper);
    }
    @Bean
    UpdateVehicleValidator updateVehicleValidator(PersonRepo personRepo, OfficeRepo officeRepo, TrafficCenterRepo trafficCenterRepo){
        return new UpdateVehicleValidator(personRepo, officeRepo, trafficCenterRepo);
    }
    @Bean
    VehicleDomainMapper vehicleDomainMapper(){
        return new VehicleDomainMapperImpl();
    }

    @Bean
    DeleteCategory deleteCategory(CategoryRepo categoryRepo, CategoryDomainMapper mapper){
        return new DeleteCategory(categoryRepo, mapper);
    }
    @Bean
    DeleteForfeit deleteForfeit(ForfeitRepo forfeitRepo, ForfeitDomainMapper mapper){
        return new DeleteForfeit(forfeitRepo, mapper);
    }
    @Bean
    DeleteGasStation deleteGasStation(GasStationRepo gasStationRepo, GasStationDomainMapper gasStationDomainMapper){
        return new DeleteGasStation(gasStationRepo, gasStationDomainMapper);
    }
    @Bean
    DeleteGroup deleteGroup(GroupRepo groupRepo, GroupDomainMapper groupDomainMapper){
        return new DeleteGroup(groupRepo, groupDomainMapper);
    }
    @Bean
    DeleteMaterial deleteMaterial(MaterialRepo materialRepo, MaterialDomainMapper materialDomainMapper){
        return new DeleteMaterial(materialRepo, materialDomainMapper);
    }
    @Bean
    DeleteOffice deleteOffice(OfficeRepo officeRepo, OfficeDomainMapper officeDomainMapper){
        return new DeleteOffice(officeRepo, officeDomainMapper);
    }
    @Bean
    DeletePartition deletePartition(PartitionRepo partitionRepo, PartitionDomainMapper partitionDomainMapper){
        return new DeletePartition(partitionRepo, partitionDomainMapper);
    }
    @Bean
    DeletePerson deletePerson(PersonRepo personRepo, PersonDomainMapper personDomainMapper){
        return new DeletePerson(personRepo, personDomainMapper);
    }
    @Bean
    DeletePriceCategory deletePriceCategory(PriceCategoryRepo priceCategoryRepo, PriceCategoryDomainMapper priceCategoryDomainMapper){
        return new DeletePriceCategory(priceCategoryRepo, priceCategoryDomainMapper);
    }
    @Bean
    DeleteRefinery deleteRefinery(RefineryRepo refineryRepo, RefineryDomainMapper refineryDomainMapper){
        return new DeleteRefinery(refineryRepo, refineryDomainMapper);
    }
    @Bean
    DeleteRegion deleteRegion(RegionRepo regionRepo, RegionDomainMapper regionDomainMapper){
        return new DeleteRegion(regionRepo, regionDomainMapper);
    }
    @Bean
    DeleteTrafficCenter deleteTrafficCenter(TrafficCenterRepo trafficCenterRepo, TrafficCenterDomainMapper trafficCenterDomainMapper){
        return new DeleteTrafficCenter(trafficCenterRepo, trafficCenterDomainMapper);
    }
    @Bean
    DeleteTrans deleteTrans(TransRepo transRepo, DomainTransMapper transDomainMapper){
        return new DeleteTrans(transRepo, transDomainMapper);
    }
    @Bean
    DeleteTransferMaterial deleteTransferMaterial(TransferMaterialRepo transferMaterialRepo, TransferMaterialDomainMapper transferMaterialDomainMapper){
        return new DeleteTransferMaterial(transferMaterialDomainMapper, transferMaterialRepo);
    }
    @Bean
    DeleteTransLine deleteTransLine(TransLineRepo transLineRepo, TransLineDomainMapper transLineDomainMapper){
        return new DeleteTransLine(transLineRepo, transLineDomainMapper);
    }
    @Bean
    DeleteTransLog deleteTransLog(TransLogRepo transLogRepo, TransLogDomainMapper transLogDomainMapper){
        return new DeleteTransLog(transLogDomainMapper, transLogRepo);
    }
    @Bean
    DeleteVehicle deleteVehicle(VehicleRepo vehicleRepo, VehicleDomainMapper vehicleDomainMapper){
        return new DeleteVehicle(vehicleRepo, vehicleDomainMapper);
    }
    @Bean
    DeleteDocument deleteDocument(DocumentRepo documentRepo, DocumentDomainMapper documentDomainMapper){
        return new DeleteDocument(documentDomainMapper, documentRepo);
    }
    @Bean
    TokenService tokenService(){
        return new TokenService();
    }

    @Bean
    EndPoints endPoints(){
        return new EndPoints();
    }

}
