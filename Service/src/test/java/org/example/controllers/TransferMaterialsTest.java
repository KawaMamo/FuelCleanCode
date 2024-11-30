package org.example.controllers;

import org.example.contract.request.create.*;
import org.example.contract.response.*;
import org.example.model.Money;
import org.example.model.TransportationType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransferMaterialsTest {

    @Autowired
    private TransferMaterialController transferMaterialController;
    @Autowired
    private TransLogController transLogController;
    @Autowired
    private TransController transController;
    @Autowired
    private RegionController regionController;
    @Autowired
    private RefineryController refineryController;
    @Autowired
    private PersonController personController;
    @Autowired
    private TrafficCenterController trafficCenterController;
    @Autowired
    private OfficeController officeController;
    @Autowired
    private VehicleController vehicleController;
    @Autowired
    private PriceCategoryController priceCategoryController;
    @Autowired
    private GasStationController gasStationController;
    @Autowired
    private GroupController groupController;
    @Autowired
    private MaterialController materialController;
    @Autowired
    private PartitionController partitionController;
    @Test
    @Disabled
    void contextLoads() {
        final CreateTransferMaterialRequest request = new CreateTransferMaterialRequest(5l, 6l, 1l, 2500L, new Money("USD", 100d));
        final TransferMaterialResponse transferMaterial = transferMaterialController.createTransferMaterial(request);
        assertNotNull(transferMaterial);
    }

    /*@Test
    void createRegion(){
        final CreateRegionRequest transRegion = new CreateRegionRequest("region 1"+ (int)(Math.random()*100), "trans region"+ (Math.random()*100)/100);
        final RegionResponse region = regionController.createRegion(transRegion);
        assertNotNull(region);
    }

    @Test
    void create_refinery(){
        final CreateRefineryRequest createRefineryRequest = new CreateRefineryRequest("ref1"+ (int)(Math.random()*100), "trans"+ (Math.random()*100)/100, 2L);
        final RefineryResponse refinery = refineryController.createRefinery(createRefineryRequest);
        assertNotNull(refinery);
    }

    @Test
    void create_person(){
        final CreatePersonRequest createPersonRequest = new CreatePersonRequest("name", "father", "mother", "12365472"+ (int)((Math.random()*1000)), "birth place", LocalDate.now());
        final PersonResponse person = personController.createPerson(createPersonRequest);
        assertNotNull(person);
    }

    @Test
    void create_traffic_center(){
        final CreateTrafficCenterRequest trafficCenter = new CreateTrafficCenterRequest("traffic center"+ (int)(Math.random()*100));
        final TrafficCenterResponse trafficCenter1 = trafficCenterController.createTrafficCenter(trafficCenter);
        assertNotNull(trafficCenter1);
    }

    @Test
    void create_office(){
        final CreateOfficeRequest createOfficeRequest = new CreateOfficeRequest("office "+ (int)(Math.random()*100));
        final OfficeResponse office = officeController.createOffice(createOfficeRequest);
        assertNotNull(office);
    }

    @Test
    void create_vehicle(){
        final CreateVehicleRequest createVehicleRequest = new CreateVehicleRequest((long) ((Math.random()*100)), ""+ (int)(Math.random()*1000000), 1L, 2500, 1L, 1L);
        final VehicleResponse vehicle = vehicleController.createVehicle(createVehicleRequest);
        assertNotNull(vehicle);
    }

    @Test
    void create_trans(){
        final CreateTransRequest createTransRequest = new CreateTransRequest(102l, 1l, 1l, TransportationType.NORMAL);
        final TransResponse trans = transController.createTrans(createTransRequest);
        assertNotNull(trans);
    }

    @Test
    void create_price_category(){
        final CreatePriceCategoryRequest createPriceCategoryRequest = new CreatePriceCategoryRequest("price category 1"+ (int)(Math.random()*100));
        final PriceCategoryResponse execute = priceCategoryController.execute(createPriceCategoryRequest);
        assertNotNull(execute);

    }

    @Test
    void create_group(){
        final var createGroupRequest = new CreateGroupRequest("group 1"+ (int)(Math.random()*100));
        final GroupResponse group = groupController.createGroup(createGroupRequest);
        assertNotNull(group);
    }

    @Test
    void create_material(){
        final CreateMaterialRequest createMaterialRequest = new CreateMaterialRequest("material 1"+ (int)(Math.random()*100));
        final MaterialResponse material = materialController.createMaterial(createMaterialRequest);
        assertNotNull(material);
    }
    @Test
    void create_gas_station(){
        final CreateGasStationRequest createGasStationRequest = new CreateGasStationRequest("gas station "+ (int)(Math.random()*100), "translation 1"+ (int)(Math.random()*100), 1L, 2L, 1L, 1L, 1L);
        final GasStationResponse gasStation = gasStationController.createGasStation(createGasStationRequest);
        assertNotNull(gasStation);
    }

    @Test
    void create_transfer_materials(){
        final var createTransferMaterialRequest = new CreateTransferMaterialRequest(3l, 102l, 1L, 45000L, new Money("USD", 10D));
        final TransferMaterialResponse transferMaterial = transferMaterialController.createTransferMaterial(createTransferMaterialRequest);
        assertNotNull(transferMaterial);
    }

    @Test
    @Disabled
    void create_partition(){
        *//*final CreatePartitionRequest createPartitionRequest = new CreatePartitionRequest(1L, 45000, 42000, new Money("SP", 12D), 52L, "notes", "extra notes", 1L);
        final PartitionResponse partition = partitionController.createPartition(createPartitionRequest);
        assertNotNull(partition);*//*
    }*/
}
