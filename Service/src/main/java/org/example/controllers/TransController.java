package org.example.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.contract.request.create.CreateDocumentRequest;
import org.example.contract.request.create.CreateTransRequest;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.contract.response.TransResponse;
import org.example.entities.TransportationEntity;
import org.example.entities.TransportationType;
import org.example.mappers.TransMapper;
import org.example.model.Transportation;
import org.example.repositories.TransRepoJpa;
import org.example.security.UserData;
import org.example.specifications.*;
import org.example.useCases.create.CreateTrans;
import org.example.useCases.delete.DeleteTrans;
import org.example.useCases.delete.DeleteTransDocuments;
import org.example.useCases.update.UpdateTrans;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("api/v1/trans")
public class TransController {

    private final CreateTrans createTrans;
    private final TransRepoJpa transRepoJpa;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final TransMapper transMapper;
    private final UpdateTrans updateTrans;
    private final DeleteTrans deleteTrans;
    private final DeleteTransDocuments deleteTransDocuments;

    public TransController(CreateTrans createTrans,
                           TransRepoJpa transRepoJpa,
                           PagedResourcesAssembler pagedResourcesAssembler,
                           TransMapper transMapper, UpdateTrans updateTrans, DeleteTrans deleteTrans, DeleteTransDocuments deleteTransDocuments) {
        this.createTrans = createTrans;
        this.transRepoJpa = transRepoJpa;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.transMapper = transMapper;
        this.updateTrans = updateTrans;
        this.deleteTrans = deleteTrans;
        this.deleteTransDocuments = deleteTransDocuments;
    }

    @PostMapping
    public TransResponse createTrans(@RequestBody CreateTransRequest request) {
        return createTrans.execute(request);
    }

    @PostMapping("/listTrans")
    public PagedModel<TransResponse> listTrans(@RequestBody TransSpecifications specifications, Pageable pageable){
        final Page<Transportation> map = transRepoJpa.findAll(specifications, pageable).map(transMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(map);
    }

    @GetMapping
    public PagedModel<TransResponse> getTrans(SearchFilter criteria, Pageable pageable){
        List<SearchCriteria> searchCriteria = CriteriaArrayToList.getCriteriaList(criteria);
        final SearchCriteria userIdCriteria = new SearchCriteria("UserId", UserData.UserId, ":", null);
        for (LinkedHashMap<String, String> role : UserData.roles) {
            if(role.get("authority").equals("TRANS_OFFICE")){
                searchCriteria.add(userIdCriteria);
            }
        }

        final FilterSpecifications<TransportationEntity> specifications = new FilterSpecifications<>(searchCriteria);
        final Page<Transportation> map = transRepoJpa.findAll(specifications, pageable).map(transMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(map);
    }

    @GetMapping("/all")
    public List<Transportation> getAll(){
        return transRepoJpa.findAll().stream().map(transMapper::entityToDomain).toList();
    }

    @PatchMapping
    public ResponseEntity<TransResponse> updateTrans(@RequestBody UpdateTransRequest request){
        return ResponseEntity.ok(updateTrans.execute(request));
    }
    @DeleteMapping("/{id}")
    public TransResponse deleteTrans(@PathVariable Long id){
        return deleteTrans.execute(id);
    }

    @DeleteMapping("/documents/{id}")
    public TransResponse deleteTransDocs(@PathVariable Long id){
        return deleteTransDocuments.execute(id);
    }

    @GetMapping("/RefineryReport/{exportType}/{id}/{start}/{end}/{type}")
    public ResponseEntity<String> getRefineryReport(@PathVariable Long id,
                                                    @PathVariable LocalDate start,
                                                    @PathVariable LocalDate end,
                                                    @PathVariable TransportationType type,
                                                    @PathVariable String exportType){

        final List<TransportationEntity> logEntityList = transRepoJpa.getTransportationEntityByRefinery(id,
                LocalDateTime.of(start, LocalTime.MIN),
                LocalDateTime.of(end, LocalTime.MAX),
                type);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now());
        params.put("refineryName", logEntityList.get(0).getRefinery().getTranslation());

        try {

            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(logEntityList);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/refineryReport.jrxml").getInputStream());
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);
            File file = null;
            if(exportType.equals("HTML")){
                file = new File("DriverReport.html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());
            }else if(exportType.equals("XLSX")){
                file = new File("DriverReport.xlsx");
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file.getAbsolutePath()));
                exporter.exportReport();
            }
            assert file != null;
            try(final FileInputStream fileInputStream = new FileInputStream(file);){
                return ResponseEntity.ok(Base64.getEncoder().encodeToString(fileInputStream.readAllBytes()));
            }
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, path = "/{id}/document")
    public ResponseEntity<TransResponse> addDocument(@PathVariable Long id,
                                                     @RequestPart String request,
                                                     @RequestPart("document") byte[] document){
        ObjectMapper mapper = new ObjectMapper();
        try {
            final CreateDocumentRequest createDocumentRequest = mapper.readValue(request, CreateDocumentRequest.class);
            createDocumentRequest.setContent(document);
            final TransResponse transResponse = updateTrans.addDocument(id, createDocumentRequest);
            return ResponseEntity.ok(transResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
