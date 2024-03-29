package org.example.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.contract.request.create.CreatePartitionRequest;
import org.example.contract.request.update.UpdatePartitionRequest;
import org.example.contract.response.PartitionResponse;
import org.example.entities.GasStationEntity;
import org.example.entities.PartitionEntity;
import org.example.entities.RegionEntity;
import org.example.entities.TransportationType;
import org.example.mappers.PartitionMapper;
import org.example.model.Partition;
import org.example.repositories.PartitionRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreatePartition;
import org.example.useCases.delete.DeletePartition;
import org.example.useCases.update.UpdatePartition;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/partition")
public class PartitionController {

    private final CreatePartition createPartition;
    private final PartitionRepository partitionRepository;
    private final PartitionMapper partitionMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdatePartition updatePartition;
    private final DeletePartition deletePartition;

    public PartitionController(CreatePartition createPartition,
                               PartitionRepository partitionRepository,
                               PartitionMapper partitionMapper,
                               PagedResourcesAssembler assembler,
                               UpdatePartition updatePartition, DeletePartition deletePartition) {
        this.createPartition = createPartition;
        this.partitionRepository = partitionRepository;
        this.partitionMapper = partitionMapper;
        this.assembler = assembler;
        this.updatePartition = updatePartition;
        this.deletePartition = deletePartition;
    }

    @PostMapping
    public PartitionResponse createPartition(@RequestBody CreatePartitionRequest request){
        return createPartition.execute(request);
    }

    @GetMapping
    PagedModel<PartitionResponse> listPartitions(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<PartitionEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Partition> page = partitionRepository.findAll(specifications, pageable).map(partitionMapper::entityToDomain);
        return assembler.toModel(page);
    }

    @PatchMapping
    ResponseEntity<PartitionResponse> updatePartition(@RequestBody UpdatePartitionRequest request){
        return ResponseEntity.ok(updatePartition.execute(request));
    }
    @DeleteMapping("/{id}")
    public PartitionResponse delete(@PathVariable Long id){
        return deletePartition.execute(id);
    }

    @GetMapping("/regionReport/{exportType}/{id}/{start}/{end}/{type}")
    public byte[] getReports(@PathVariable Long id,
                             @PathVariable LocalDate start,
                             @PathVariable LocalDate end,
                             @PathVariable String exportType,
                             @PathVariable TransportationType type){

        final List<PartitionEntity> partitionEntities = partitionRepository.getPartitionEntities(id,
                LocalDateTime.of(start, LocalTime.parse("00:00:00")),
                LocalDateTime.of(end, LocalTime.parse("23:59:59")),
                type);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        try {

            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(partitionEntities);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/regionDesign.jrxml").getInputStream());
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);
            File file = null;
            if(exportType.equals("HTML")){
                file = new File("templates/regionReport.html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());
            }else if(exportType.equals("XLSX")){
                file = new File("regionReport.xlsx");
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file.getAbsolutePath()));
                exporter.exportReport();
            }

            assert file != null;
            try(final FileInputStream fileInputStream = new FileInputStream(file);){
                return fileInputStream.readAllBytes();
            }
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/regionResponse/{exportType}/{id}/{start}/{end}/{type}")
    public ResponseEntity getReportAsResponse(@PathVariable Long id,
                             @PathVariable LocalDate start,
                             @PathVariable LocalDate end,
                             @PathVariable String exportType,
                             @PathVariable TransportationType type){

        final List<PartitionEntity> partitionEntities = partitionRepository.getPartitionEntities(id,
                LocalDateTime.of(start, LocalTime.parse("00:00:00")),
                LocalDateTime.of(end, LocalTime.parse("23:59:59")),
                type);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(partitionEntities);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/regionDesign.jrxml").getInputStream());
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);
            File file = null;
            if(exportType.equals("HTML")){
                file = new File("regionReport.html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());
            }else if(exportType.equals("XLSX")){
                file = new File("regionReport.xlsx");
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file.getAbsolutePath()));
                exporter.exportReport();
            }

            assert file != null;
            try(final FileInputStream fileInputStream = new FileInputStream(file);){
                final HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                return new ResponseEntity<>(fileInputStream.readAllBytes(), httpHeaders, HttpStatus.OK);

            }
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/regionResponse64/{exportType}/{id}/{start}/{end}/{type}")
    public ResponseEntity<String> getReport64Response(@PathVariable Long id,
                                              @PathVariable LocalDate start,
                                              @PathVariable LocalDate end,
                                              @PathVariable String exportType,
                                              @PathVariable TransportationType type){

        final List<PartitionEntity> partitionEntities = partitionRepository.getPartitionEntities(id,
                LocalDateTime.of(start, LocalTime.parse("00:00:00")),
                LocalDateTime.of(end, LocalTime.parse("23:59:59")),
                type);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(partitionEntities);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/regionDesign.jrxml").getInputStream());
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);
            File file = null;
            if(exportType.equals("HTML")){
                file = new File("regionReport.html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());
            }else if(exportType.equals("XLSX")){
                file = new File("regionReport.xlsx");
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

    @GetMapping("/partitionPrint/{id}/{username}")
    public ResponseEntity<String> getPartitionPrint(@PathVariable Long id, @PathVariable String username){

        final PartitionEntity partition = partitionRepository.getReferenceById(id);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        params.put("transportationId", partition.getTransportationEntity().getId().toString());
        params.put("vehicleNumber", partition.getTransportationEntity().getVehicle().getPlateNumber());
        params.put("driverName", partition.getTransportationEntity().getVehicle().getDriver().getName());
        params.put("gasStation", partition.getGasStation().getTranslation());
        params.put("amount", NumberFormat.getInstance().format(partition.getAmount()));
        params.put("material", partition.getMaterial().getName());
        params.put("transportationDate", partition.getTransportationEntity().getCreatedAt().toString());
        params.put("category", partition.getNotes());
        params.put("user", username);
        params.put("refinery", partition.getTransportationEntity().getRefinery().getTranslation());

        try {
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/transTicket.jrxml").getInputStream());
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, new JREmptyDataSource());
            File file = new File("transTicket.html");
            JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());

            try(final FileInputStream fileInputStream = new FileInputStream(file);){
                return ResponseEntity.ok(Base64.getEncoder().encodeToString(fileInputStream.readAllBytes()));
            }
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
