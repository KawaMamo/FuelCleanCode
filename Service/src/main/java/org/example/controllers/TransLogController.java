package org.example.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;
import org.example.contract.request.create.CreateTransLogRequest;
import org.example.contract.request.update.UpdateTransLogRequest;
import org.example.contract.response.TransLogResponse;
import org.example.entities.TransLogEntity;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

    @GetMapping("/jasper/{id}/{start}/{end}")
    public byte[] getReports(@PathVariable Long id, @PathVariable LocalDate start, @PathVariable LocalDate end){

        final List<TransLogEntity> logEntityList = transLogRepository.findAll();
        String jreXmlTemplatePath = "D:\\fuelRefactored\\FuelCleanCode\\Service\\src\\main\\resources\\templates\\regionDesign.jrxml";
        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now());

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(logEntityList);
            JasperReport regionReport = JasperCompileManager.compileReport(jreXmlTemplatePath);
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);

            JasperExportManager.exportReportToHtmlFile(jasperPrint,
                    "D:\\fuelRefactored\\FuelCleanCode\\Service\\src\\main\\resources\\templates\\test.html");
            final File file = new File("D:\\fuelRefactored\\FuelCleanCode\\Service\\src\\main\\resources\\templates\\test.html");
            final FileInputStream fileInputStream = new FileInputStream(file);
            return fileInputStream.readAllBytes();
        } catch (JRException | IOException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/jasperXls/{id}/{start}/{end}")
    public byte[] getXlsReport(@PathVariable Long id, @PathVariable LocalDate start, @PathVariable LocalDate end){
        String jreXmlTemplatePath = "D:\\fuelRefactored\\FuelCleanCode\\Service\\src\\main\\resources\\templates\\regionDesign.jrxml";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("start", start);
        params.put("end", end);
        params.put("title", "تقرير منطقة");
        params.put("regionName", "منطقة");
        params.put("plateNumber", "456782");
        params.put("driverName", "driverName");
        params.put("amount", 89000D);
        params.put("material", "material");
        params.put("refinery", "ref1");
        params.put("gasStation", "الوجهة");
        params.put("notesField", "notesField");
        params.put("dateField", LocalDate.now());
        params.put("now", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        params.put("recordCount", 1);
        try {
            JasperReport regionReport = JasperCompileManager.compileReport(jreXmlTemplatePath);
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, new JREmptyDataSource());

            JRXlsxExporter exporter = new JRXlsxExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(
                    new SimpleOutputStreamExporterOutput("employeeReport.xlsx"));

            exporter.exportReport();

            return null;
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
