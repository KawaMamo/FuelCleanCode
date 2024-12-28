package org.example.controllers;

import org.example.contract.request.create.*;
import org.example.contract.response.*;
import org.example.entities.TransportationType;
import org.example.model.Money;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

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
    /*@Test
    @Disabled
    void contextLoads() {
        final OfficeResponse office1 = officeController.createOffice(new CreateOfficeRequest("office 1"));
        final OfficeResponse office2 = officeController.createOffice(new CreateOfficeRequest("office 2"));
        assertNotNull(office1);
        assertNotNull(office2);

        final PersonResponse person1 = personController.createPerson(new CreatePersonRequest("name "+(int)((Math.random()*100)) ,
                "father",
                "mother",
                "12365472"+ (int)((Math.random()*1000)),
                "birth place",
                LocalDate.now()));
        final PersonResponse person2 = personController.createPerson(new CreatePersonRequest("name "+(int)((Math.random()*100)) ,
                "father",
                "mother",
                "12365472"+ (int)((Math.random()*1000)),
                "birth place",
                LocalDate.now()));
        assertNotNull(person1);
        assertNotNull(person2);

        final TrafficCenterResponse trafficCenter1 = trafficCenterController.createTrafficCenter(
                new CreateTrafficCenterRequest("traffic center"+ (int)(Math.random()*100)));
        assertNotNull(trafficCenter1);

        final TrafficCenterResponse trafficCenter2 = trafficCenterController.createTrafficCenter(
                new CreateTrafficCenterRequest("traffic center"+ (int)(Math.random()*100)));
        assertNotNull(trafficCenter2);

        final VehicleResponse vehicle1 = vehicleController.createVehicle(new CreateVehicleRequest((long) ((Math.random()*100)),
                ""+ (int)(Math.random()*1000000),
                trafficCenter1.getId(),
                2500,
                office1.getId(),
                person1.getId()));
        assertNotNull(vehicle1);

        final VehicleResponse vehicle2 = vehicleController.createVehicle(new CreateVehicleRequest((long) ((Math.random()*100)),
                ""+ (int)(Math.random()*1000000),
                trafficCenter1.getId(),
                2500,
                office1.getId(),
                person1.getId()));
        assertNotNull(vehicle2);

        final RegionResponse region1 = regionController.createRegion(
                new CreateRegionRequest("region "+ (int)(Math.random()*100), "trans region"+ (int)(Math.random()*100)));
        assertNotNull(region1);

        final RegionResponse region2 = regionController.createRegion(
                new CreateRegionRequest("region "+ (int)(Math.random()*100), "trans region"+ (int)(Math.random()*100)));
        assertNotNull(region2);

        final RefineryResponse refinery1 = refineryController.createRefinery(new CreateRefineryRequest("ref "+ (int)(Math.random()*100),
                "trans"+ (int)(Math.random()*100),
                region1.getId()));
        assertNotNull(refinery1);

        final RefineryResponse refinery2 = refineryController.createRefinery(new CreateRefineryRequest("ref "+ (int)(Math.random()*100),
                "trans"+ (int)(Math.random()*100),
                region2.getId()));
        assertNotNull(refinery2);

        final TransResponse trans1 = transController.createTrans(new CreateTransRequest(refinery1.getId(),
                vehicle1.getId(),
                25000L,
                TransportationType.NORMAL));
        assertNotNull(trans1);

        final TransResponse trans2 = transController.createTrans(new CreateTransRequest(refinery1.getId(),
                vehicle1.getId(),
                25000L,
                TransportationType.NORMAL));
        assertNotNull(trans2);

        final PriceCategoryResponse priceCategory1 = priceCategoryController.execute(
                new CreatePriceCategoryRequest("price category "+ (int)(Math.random()*100)));
        assertNotNull(priceCategory1);

        final PriceCategoryResponse priceCategory2 = priceCategoryController.execute(
                new CreatePriceCategoryRequest("price category "+ (int)(Math.random()*100)));
        assertNotNull(priceCategory2);

        final GroupResponse group1 = groupController.createGroup(new CreateGroupRequest("group "+ (int)(Math.random()*100)));
        assertNotNull(group1);

        final GroupResponse group2 = groupController.createGroup(new CreateGroupRequest("group "+ (int)(Math.random()*100)));
        assertNotNull(group2);

        final MaterialResponse material1 = materialController.createMaterial(new CreateMaterialRequest("material "+ (int)(Math.random()*100)));
        assertNotNull(material1);

        final MaterialResponse material2 = materialController.createMaterial(new CreateMaterialRequest("material "+ (int)(Math.random()*100)));
        assertNotNull(material2);

        final GasStationResponse gasStation1 = gasStationController.createGasStation(new CreateGasStationRequest("gas station "+ (int)(Math.random()*100),
                "translation "+ (int)(Math.random()*100),
                priceCategory1.getId(),
                region1.getId(),
                person1.getId(),
                group1.getId(),
                material1.getId()));
        assertNotNull(gasStation1);

        final GasStationResponse gasStation2 = gasStationController.createGasStation(new CreateGasStationRequest("gas station "+ (int)(Math.random()*100),
                "translation "+ (int)(Math.random()*100),
                priceCategory2.getId(),
                region2.getId(),
                person2.getId(),
                group2.getId(),
                material2.getId()));
        assertNotNull(gasStation2);

        final TransferMaterialResponse transferMaterial1 = transferMaterialController.createTransferMaterial(
                new CreateTransferMaterialRequest(gasStation1.getId(),
                gasStation2.getId(),
                material1.getId(),
                45000L,
                new Money("USD", 10D),
                TransportationType.NORMAL));
        assertNotNull(transferMaterial1);

        final TransferMaterialResponse transferMaterial2 = transferMaterialController.createTransferMaterial(
                new CreateTransferMaterialRequest(gasStation1.getId(),
                        gasStation2.getId(),
                        material2.getId(),
                        45000L,
                        new Money("USD", 12D),
                        TransportationType.NORMAL));
        assertNotNull(transferMaterial2);

        *//*final PartitionResponse partition1 = partitionController.createPartition(new CreatePartitionRequest(material1.getId(),
                45000,
                42000,
                new Money("SP", 12D),
                gasStation1.getId(),
                "notes",
                "extra notes",
                trans1.getId()));
        assertNotNull(partition1);

        final PartitionResponse partition2 = partitionController.createPartition(new CreatePartitionRequest(material2.getId(),
                45000,
                42000,
                new Money("SP", 14D),
                gasStation2.getId(),
                "notes",
                "extra notes",
                trans2.getId()));
        assertNotNull(partition2);*//*

    }*/
    @Test
    void testClientsReceivedMaterials() throws Exception {
        final ResponseEntity<String> html = partitionController.getClientReceivedMaterials(6L,
                LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-12-30"),
                "HTML",
                TransportationType.NORMAL);
        final byte[] decode = Base64.getDecoder().decode(html.getBody());
        String htmlContent = new String(decode);
        final boolean b = htmlContent.startsWith("<!DOCTYPE");
        assertTrue(b);
    }
}
