package org.example.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.example.auxiliary.TransLogAggregated;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.entities.TransLogEntity;
import org.example.entities.TransportationType;
import org.example.mappers.TransLogMapper;
import org.example.model.TransLog;
import org.example.repositories.TransLogRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateTransLog;
import org.example.useCases.delete.DeleteTransLog;
import org.example.useCases.update.UpdateTransLog;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@RestController
@RequestMapping("api/v1/trans-log")
public class TransLogController {

    private final CreateTransLog createTransLog;
    private final TransLogRepository transLogRepository;
    private final TransLogMapper transLogMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdateTransLog updateTransLog;
    private final DeleteTransLog deleteTransLog;

    public TransLogController(CreateTransLog createTransLog,
                              TransLogRepository transLogRepository,
                              TransLogMapper transLogMapper,
                              PagedResourcesAssembler assembler, UpdateTransLog updateTransLog, DeleteTransLog deleteTransLog) {
        this.createTransLog = createTransLog;
        this.transLogRepository = transLogRepository;
        this.transLogMapper = transLogMapper;
        this.assembler = assembler;
        this.updateTransLog = updateTransLog;
        this.deleteTransLog = deleteTransLog;
    }

    @PostMapping
    public TransLogResponse createTransLog(@RequestBody CreateTransLogRequest request){
        return createTransLog.execute(request);
    }

    @GetMapping
    public PagedModel<TransLogResponse> listTransLogs(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<TransLogEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<TransLog> page = transLogRepository.findAll(specifications, pageable).map(transLogMapper::entityToDomain);
        return assembler.toModel(page);
    }
    @PatchMapping
    public ResponseEntity<TransLogResponse> updateTransLog(@RequestBody UpdateTransLogRequest request){
        return ResponseEntity.ok(updateTransLog.execute(request));
    }
    @DeleteMapping("/{id}")
    public TransLogResponse deleteTransLog(@PathVariable Long id){
        return deleteTransLog.execute(id);
    }

    @GetMapping("/driverReport/{exportType}/{id}/{start}/{end}/{type}")
    public byte[] getDriverReport(@PathVariable Long id,
                                  @PathVariable LocalDate start,
                                  @PathVariable LocalDate end,
                                  @PathVariable TransportationType type,
                                  @PathVariable String exportType){

        final List<TransLogEntity> logEntityList = transLogRepository.getTransLogEntities(id,
                LocalDateTime.of(start, LocalTime.MIN),
                LocalDateTime.of(end, LocalTime.MAX),
                type);

        String jreXmlTemplatePath = "D:\\fuelRefactored\\FuelCleanCode\\Service\\src\\main\\resources\\templates\\driverReport.jrxml";
        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now());

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(logEntityList);
            JasperReport regionReport = JasperCompileManager.compileReport(jreXmlTemplatePath);
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);
            File file = null;
            if(exportType.equals("HTML")){
                file = new File("D:\\fuelRefactored\\FuelCleanCode\\Service\\src\\main\\resources\\templates\\DriverReport.html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());
            }else if(exportType.equals("XLSX")){
                file = new File("D:\\fuelRefactored\\FuelCleanCode\\Service\\src\\main\\resources\\templates\\DriverReport.xlsx");
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

    @GetMapping("/driverReport64/{exportType}/{id}/{start}/{end}/{type}")
    public ResponseEntity<String> getDriverReport64(@PathVariable Long id,
                                                    @PathVariable LocalDate start,
                                                    @PathVariable LocalDate end,
                                                    @PathVariable TransportationType type,
                                                    @PathVariable String exportType){

        final List<TransLogEntity> logEntityList = transLogRepository.getTransLogEntities(id,
                LocalDateTime.of(start, LocalTime.MIN),
                LocalDateTime.of(end, LocalTime.MAX),
                type);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now());
        params.put("driverName", logEntityList.get(0).getVehicle().getDriver().getName());
        params.put("vehicleNumber", logEntityList.get(0).getVehicle().getPlateNumber());
        params.put("officeName", logEntityList.get(0).getVehicle().getOffice().getName());
        params.put("vehicleSize", NumberFormat.getInstance().format(logEntityList.get(0).getVehicle().getSize()));
        params.put("notes", logEntityList.get(0).getNotes());
        params.put("imgParameter", "test/test.png");

        try {

            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(logEntityList);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/driverReport.jrxml").getInputStream());
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


    @GetMapping("/RefineryReport/{exportType}/{id}/{start}/{end}/{type}")
    public ResponseEntity<String> getRefineryReport(@PathVariable Long id,
                                                    @PathVariable LocalDate start,
                                                    @PathVariable LocalDate end,
                                                    @PathVariable TransportationType type,
                                                    @PathVariable String exportType){

        final List<TransLogEntity> logEntityList = transLogRepository.getTransLogEntitiesByRefinery(id,
                LocalDateTime.of(start, LocalTime.MIN),
                LocalDateTime.of(end, LocalTime.MAX),
                type);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now());
        params.put("refineryName", logEntityList.get(0).getTransportation().getRefinery().getTranslation());

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

    @GetMapping("/officeReport/{exportType}/{id}/{start}/{end}/{type}")
    public ResponseEntity<String> getOfficeReport(@PathVariable Long id,
                                                    @PathVariable LocalDate start,
                                                    @PathVariable LocalDate end,
                                                    @PathVariable TransportationType type,
                                                    @PathVariable String exportType){

        final List<Object[]> transObj = transLogRepository.getTransObj(id,
                LocalDateTime.of(start, LocalTime.MIN),
                LocalDateTime.of(end, LocalTime.MAX),
                type);
        List<TransLogAggregated> transLogAggregatedList = new ArrayList<>();
        for (Object[] objects : transObj) {
            transLogAggregatedList.add(new TransLogAggregated((TransLogEntity) objects[0], (Double) objects[1], (Long) objects[2]));
        }


        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now());
        params.put("startDate", start);
        params.put("endDate", end);
        params.put("officeName", transLogAggregatedList.get(0).transLogEntity.getVehicle().getOffice().getName());
        try {

            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(transLogAggregatedList);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/officeReport.jrxml").getInputStream());
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);
            File file = null;
            if(exportType.equals("HTML")){
                file = new File("OfficeReport.html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());
            }else if(exportType.equals("XLSX")){
                file = new File("OfficeReport.xlsx");
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
}
