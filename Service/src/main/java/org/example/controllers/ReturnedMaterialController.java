package org.example.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.contract.request.create.CreateReturnedMaterialRequest;
import org.example.contract.request.update.UpdateReturnedMaterialRequest;
import org.example.contract.response.ReturnedMaterialResponse;
import org.example.entities.ReturnedMaterial;
import org.example.mappers.ReturnedMaterialMapper;
import org.example.repositories.ReturnedMaterialRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateReturnedMaterial;
import org.example.useCases.delete.DeleteReturnedMaterial;
import org.example.useCases.update.UpdateReturnedMaterial;
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
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/returned-material")
public class ReturnedMaterialController {

    private final CreateReturnedMaterial createReturnedMaterial;
    private final UpdateReturnedMaterial updateReturnedMaterial;
    private final DeleteReturnedMaterial deleteReturnedMaterial;
    private final ReturnedMaterialRepository returnedMaterialRepository;
    private final ReturnedMaterialMapper returnedMaterialMapper;
    private final PagedResourcesAssembler assembler;

    public ReturnedMaterialController(CreateReturnedMaterial createReturnedMaterial,
                                      UpdateReturnedMaterial updateReturnedMaterial,
                                      DeleteReturnedMaterial deleteReturnedMaterial, ReturnedMaterialRepository returnedMaterialRepository,
                                      ReturnedMaterialMapper returnedMaterialMapper,
                                      PagedResourcesAssembler assembler) {
        this.createReturnedMaterial = createReturnedMaterial;
        this.updateReturnedMaterial = updateReturnedMaterial;
        this.deleteReturnedMaterial = deleteReturnedMaterial;
        this.returnedMaterialRepository = returnedMaterialRepository;
        this.returnedMaterialMapper = returnedMaterialMapper;
        this.assembler = assembler;
    }

    @PostMapping
    public ReturnedMaterialResponse createReturnedMaterial(@RequestBody CreateReturnedMaterialRequest request){
        return createReturnedMaterial.execute(request);
    }

    @GetMapping
    public PagedModel<ReturnedMaterialResponse> listReturnedMaterials(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<ReturnedMaterial> specifications = new FilterSpecifications<>(criteriaList);
        final Page<org.example.model.ReturnedMaterial> page = returnedMaterialRepository
                .findAll(specifications, pageable)
                .map(returnedMaterialMapper::entityToDomain);
        return assembler.toModel(page);
    }

    @PatchMapping
    ResponseEntity<ReturnedMaterialResponse> update(@RequestBody UpdateReturnedMaterialRequest request){
        return ResponseEntity.ok(updateReturnedMaterial.execute(request));
    }

    @DeleteMapping("/{id}")
    public ReturnedMaterialResponse delete(@PathVariable Long id){
        return deleteReturnedMaterial.execute(id);
    }

    @GetMapping("/totalReturnedMaterials/{gasStationId}")
    public List<String[]> getTotalReturnedMaterials(@PathVariable Long gasStationId){
        return returnedMaterialRepository.getReturnedMaterials(gasStationId);
    }

    @GetMapping("/returnedMaterialsReport/{exportType}/{id}/{start}/{end}")
    public ResponseEntity<String> getReturnedMaterialsReport(@PathVariable Long id,
                                                             @PathVariable LocalDate start,
                                                             @PathVariable LocalDate end,
                                                             @PathVariable String exportType){
        final List<ReturnedMaterial> returnedMaterialsList = returnedMaterialRepository.getReturnedMaterialsList(id,
                LocalDateTime.of(start, LocalTime.parse("00:00:00")),
                LocalDateTime.of(end, LocalTime.parse("23:59:59")));

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        params.put("dateSpan", LocalDate.from(start)+"-"+LocalDate.from(end));
        params.put("clientName", returnedMaterialsList.get(0).getGasStation().getName());

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(returnedMaterialsList);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/returnedMaterials.jrxml").getInputStream());
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
}
