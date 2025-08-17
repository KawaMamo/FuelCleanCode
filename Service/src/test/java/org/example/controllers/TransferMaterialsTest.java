package org.example.controllers;

import jakarta.transaction.Transactional;
import org.example.contract.request.create.*;
import org.example.contract.response.*;
import org.example.entities.TransportationType;
import org.example.model.Money;
import org.example.specifications.SearchFilter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

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
    @Autowired
    private ClientPaymentController clientPaymentController;
    @Autowired
    private ReturnedMaterialController returnedMaterialController;

    @Autowired
    private ForfeitController forfeitController;

    @Autowired
    TransLineController transLineController;

    //@Test
    //void contextLoads() {
        /*final OfficeResponse office1 = officeController.createOffice(new CreateOfficeRequest("office 1"));
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
                new CreateTrafficCenterRequest("traffic center 1"));
        assertNotNull(trafficCenter1);

        final TrafficCenterResponse trafficCenter2 = trafficCenterController.createTrafficCenter(
                new CreateTrafficCenterRequest("traffic center 2"));
        assertNotNull(trafficCenter2);

        final VehicleResponse vehicle1 = vehicleController.createVehicle(new CreateVehicleRequest((long) ((Math.random()*100)),
                ""+ (int)(Math.random()*1000000),
                trafficCenter1.getId(),
                25000,
                office1.getId(),
                person1.getId()));
        assertNotNull(vehicle1);

        final VehicleResponse vehicle2 = vehicleController.createVehicle(new CreateVehicleRequest((long) ((Math.random()*100)),
                ""+ (int)(Math.random()*1000000),
                trafficCenter2.getId(),
                35000,
                office2.getId(),
                person2.getId()));
        assertNotNull(vehicle2);

        final RegionResponse region1 = regionController.createRegion(
                new CreateRegionRequest("region 1", "trans region 1"));
        assertNotNull(region1);

        final RegionResponse region2 = regionController.createRegion(
                new CreateRegionRequest("region 2", "trans region 2"));
        assertNotNull(region2);

        final RefineryResponse refinery1 = refineryController.createRefinery(new CreateRefineryRequest("ref 1",
                "trans ref 1",
                region1.getId()));
        assertNotNull(refinery1);

        final RefineryResponse refinery2 = refineryController.createRefinery(new CreateRefineryRequest("ref 2",
                "trans redf 2",
                region2.getId()));
        assertNotNull(refinery2);

        final TransResponse trans1 = transController.createTrans(new CreateTransRequest(refinery1.getId(),
                vehicle1.getId(),
                vehicle1.getSize().longValue(),
                org.example.model.TransportationType.NORMAL,
                false,
                LocalDateTime.now()));
        assertNotNull(trans1);

        final TransResponse trans2 = transController.createTrans(new CreateTransRequest(refinery2.getId(),
                vehicle2.getId(),
                vehicle2.getSize().longValue(),
                org.example.model.TransportationType.NORMAL,
                false,
                LocalDateTime.now()));
        assertNotNull(trans2);

        final PriceCategoryResponse priceCategory1 = priceCategoryController.execute(
                new CreatePriceCategoryRequest("price category 1"));
        assertNotNull(priceCategory1);

        final PriceCategoryResponse priceCategory2 = priceCategoryController.execute(
                new CreatePriceCategoryRequest("price category 2"));
        assertNotNull(priceCategory2);

        final GroupResponse group1 = groupController.createGroup(new CreateGroupRequest("group 1"));
        assertNotNull(group1);

        final GroupResponse group2 = groupController.createGroup(new CreateGroupRequest("group 2"));
        assertNotNull(group2);

        final MaterialResponse material1 = materialController.createMaterial(new CreateMaterialRequest("material 1"));
        assertNotNull(material1);

        final MaterialResponse material2 = materialController.createMaterial(new CreateMaterialRequest("material 2"));
        assertNotNull(material2);

        final GasStationResponse gasStation1 = gasStationController.createGasStation(
                new CreateGasStationRequest("gas station 1",
                "translation 1",
                priceCategory1.getId(),
                region1.getId(),
                person1.getId(),
                group1.getId(),
                material1.getId()));
        assertNotNull(gasStation1);

        final GasStationResponse gasStation2 = gasStationController.createGasStation(
                new CreateGasStationRequest("gas station 2",
                "translation 2",
                priceCategory2.getId(),
                region2.getId(),
                person2.getId(),
                group2.getId(),
                material2.getId()));
        assertNotNull(gasStation2);

        final TransLineResponse transLine1 = transLineController.execute(new CreateTransLineRequest(region1.getId(),
                region2.getId(),
                "USD",
                100D));


        final TransferMaterialResponse transferMaterial1 = transferMaterialController.createTransferMaterial(
                new CreateTransferMaterialRequest(gasStation1.getId(),
                gasStation2.getId(),
                material1.getId(),
                45000L,
                transLine1.getFee(),
                org.example.model.TransportationType.NORMAL));
        assertNotNull(transferMaterial1);


        final TransferMaterialResponse transferMaterial2 = transferMaterialController.createTransferMaterial(
                new CreateTransferMaterialRequest(gasStation2.getId(),
                        gasStation1.getId(),
                        material2.getId(),
                        45000L,
                        transLine1.getFee(),
                        org.example.model.TransportationType.NORMAL));
        assertNotNull(transferMaterial2);

        transLogController.createTransLog(new CreateTransLogRequest(vehicle1.getId(),
                transLine1.getId(),
                transLine1.getFee(),
                trans1.getId(),
                "notes"));*/
        /*final PartitionResponse partition1 = partitionController.createPartition(new CreatePartitionRequest(material1.getId(),
                45000,
                42000,
                new Money("SP", 12D),
                gasStation1.getId(),
                "notes",
                "extra notes",
                trans1.getId()));
        assertNotNull(partition1);*/

        /*final PartitionResponse partition2 = partitionController.createPartition(new CreatePartitionRequest(material2.getId(),
                45000,
                42000,
                new Money("SP", 14D),
                gasStation2.getId(),
                "notes",
                "extra notes",
                trans2.getId()));
        assertNotNull(partition2);*/

    //}
    /*@Test
    @Disabled
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

    @Test
    void testClientsPayments() throws Exception {
        final List<String[]> totalPayments = clientPaymentController.getTotalPayments(6L);
        assertNotNull(totalPayments);
    }

    @Test
    void testPartitionsSumForClient() throws Exception {
        partitionController.getTotalReceivedMaterials(6L);
    }

    @Test
    void testReturnedMaterialsSum() throws Exception {
        returnedMaterialController.getTotalReturnedMaterials(6L);
    }

    @Test
    void testTransferToClient(){
        transferMaterialController.getTotalTransfersTo(6L);
    }

    @Test
    @Disabled
    void testPaymentsReport(){
        final ResponseEntity<String> html = clientPaymentController.getClientPayments(6L, LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-12-30"),
                "HTML");
        final byte[] decode = Base64.getDecoder().decode(html.getBody());
        String htmlContent = new String(decode);
        final boolean b = htmlContent.startsWith("<!DOCTYPE");
        assertTrue(b);
    }

    @Test
    @Disabled
    void testReturnedMaterialsReport() throws Exception {
        final ResponseEntity<String> html = returnedMaterialController.getReturnedMaterialsReport(6L, LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-12-30"),
                "HTML");
        final byte[] decode = Base64.getDecoder().decode(html.getBody());
        String htmlContent = new String(decode);
        final boolean b = htmlContent.startsWith("<!DOCTYPE");
        assertTrue(b);
    }

    @Test
    void testTransferReport() throws Exception {
        final ResponseEntity<String> html = transferMaterialController.getTransferReport(6L, LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-12-30"),
                "HTML",
                TransportationType.NORMAL);
        final byte[] decode = Base64.getDecoder().decode(html.getBody());
        String htmlContent = new String(decode);
        final boolean b = htmlContent.startsWith("<!DOCTYPE");
        assertTrue(b);
    }

    @Test
    @Disabled
    void testForfeitReport() throws Exception {
        final ResponseEntity<String> html = forfeitController.getForfeitReport(1L,
                LocalDate.parse("2024-01-01"),
                LocalDate.parse("2024-12-30"),
                "HTML");
        final byte[] decode = Base64.getDecoder().decode(html.getBody());
        String htmlContent = new String(decode);
        final boolean b = htmlContent.startsWith("<!DOCTYPE");
        assertTrue(b);
    }

    @Test
    void testPrinciple() throws Exception {
        final SearchFilter searchFilter = new SearchFilter(new String[]{"id"}, new String[]{"0"}, new String[]{">"}, null);

    }*/
}
