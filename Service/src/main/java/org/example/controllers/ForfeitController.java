package org.example.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.contract.request.create.CreateForfeitRequest;
import org.example.contract.request.update.UpdateForfeitRequest;
import org.example.contract.response.ForfeitResponse;
import org.example.entities.ForfeitEntity;
import org.example.entities.TransportationType;
import org.example.mappers.ForfeitMapper;
import org.example.model.Forfeit;
import org.example.repositories.ForfeitRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateForfeit;
import org.example.useCases.delete.DeleteForfeit;
import org.example.useCases.update.UpdateForfeit;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@RestController
@RequestMapping("api/v1/forfeit")
public class ForfeitController {

    private final CreateForfeit createForfeit;
    private final ForfeitRepository forfeitRepository;
    private final ForfeitMapper forfeitMapper;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final UpdateForfeit updateForfeit;
    private final DeleteForfeit deleteForfeit;

    public ForfeitController(CreateForfeit createForfeit,
                             ForfeitRepository forfeitRepository,
                             ForfeitMapper forfeitMapper,
                             PagedResourcesAssembler pagedResourcesAssembler,
                             UpdateForfeit updateForfeit, DeleteForfeit deleteForfeit) {
        this.createForfeit = createForfeit;
        this.forfeitRepository = forfeitRepository;
        this.forfeitMapper = forfeitMapper;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.updateForfeit = updateForfeit;
        this.deleteForfeit = deleteForfeit;
    }

    @PostMapping
    public ForfeitResponse createForfeit(@RequestBody CreateForfeitRequest request){
        return createForfeit.execute(request);
    }

    @GetMapping
    public PagedModel<ForfeitResponse> listForfeit(SearchFilter searchFilter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilter);
        final FilterSpecifications<ForfeitEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Forfeit> page = forfeitRepository.findAll(specifications, pageable).map(forfeitMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @PatchMapping
    public ResponseEntity<ForfeitResponse> updateForfeit(@RequestBody UpdateForfeitRequest request){
        return ResponseEntity.ok(updateForfeit.execute(request));
    }
    @DeleteMapping("/{id}")
    public ForfeitResponse deleteForfeit(@PathVariable Long id){
        return deleteForfeit.execute(id);
    }

    @GetMapping("/forfeitReport/{exportType}/{id}/{start}/{end}")
    public ResponseEntity<String> getForfeitReport(@PathVariable Long id,
                                                    @PathVariable LocalDate start,
                                                    @PathVariable LocalDate end,
                                                    @PathVariable String exportType){

        final List<ForfeitEntity> forfeitEntities = forfeitRepository.getForfeitEntityByOfficeId(id,
                LocalDateTime.of(start, LocalTime.parse("00:00:00")),
                LocalDateTime.of(end, LocalTime.parse("23:59:59")));

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        params.put("dateSpan", LocalDate.from(start)+"-"+LocalDate.from(end));
        String officeName;
        if(Objects.nonNull(forfeitEntities) && Objects.nonNull(forfeitEntities.get(0)))
            officeName = forfeitEntities.get(0).getVehicles().getOffice().getName();
        else officeName = "";
        params.put("officeName", officeName);

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(forfeitEntities);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/forfeitReport.jrxml").getInputStream());
            final JasperPrint jasperPrint = JasperFillManager.fillReport(regionReport, params, jrBeanCollectionDataSource);
            File file = null;
            if(exportType.equals("HTML")){
                file = new File("forfeitReport.html");
                JasperExportManager.exportReportToHtmlFile(jasperPrint,
                        file.getAbsolutePath());
            }else if(exportType.equals("XLSX")){
                file = new File("forfeitReport.xlsx");
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
