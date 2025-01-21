package org.example.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.contract.request.create.CreateTransferMaterialRequest;
import org.example.contract.request.update.UpdateTransferMaterialRequest;
import org.example.contract.response.TransferMaterialResponse;
import org.example.entities.TransferMaterialsEntity;
import org.example.entities.TransportationType;
import org.example.mappers.TransferMaterialsMapper;
import org.example.model.TransferMaterials;
import org.example.repositories.GasStationRepository;
import org.example.repositories.TransferMaterialRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateTransferMaterial;
import org.example.useCases.delete.DeleteTransferMaterial;
import org.example.useCases.update.UpdateTransferMaterial;
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
@RequestMapping("api/v1/transfer-materials")
public class TransferMaterialController {

    private final CreateTransferMaterial createTransferMaterial;
    private final TransferMaterialRepository transferMaterialRepository;
    private final TransferMaterialsMapper transferMaterialsMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdateTransferMaterial updateTransferMaterial;
    private final DeleteTransferMaterial deleteTransferMaterial;
    private final GasStationRepository gasStationRepository;

    public TransferMaterialController(CreateTransferMaterial createTransferMaterial,
                                      TransferMaterialRepository transferMaterialRepository,
                                      TransferMaterialsMapper transferMaterialsMapper,
                                      PagedResourcesAssembler assembler,
                                      UpdateTransferMaterial updateTransferMaterial,
                                      DeleteTransferMaterial deleteTransferMaterial,
                                      GasStationRepository gasStationRepository) {
        this.createTransferMaterial = createTransferMaterial;
        this.transferMaterialRepository = transferMaterialRepository;
        this.transferMaterialsMapper = transferMaterialsMapper;
        this.assembler = assembler;
        this.updateTransferMaterial = updateTransferMaterial;
        this.deleteTransferMaterial = deleteTransferMaterial;
        this.gasStationRepository = gasStationRepository;
    }

    @PostMapping
    public TransferMaterialResponse createTransferMaterial(@RequestBody CreateTransferMaterialRequest request){
        return createTransferMaterial.execute(request);
    }

    @GetMapping
    public PagedModel<TransferMaterialResponse> listTransfers(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<TransferMaterialsEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<TransferMaterials> page = transferMaterialRepository.findAll(specifications, pageable)
                .map(transferMaterialsMapper::entityToDomain);
        return assembler.toModel(page);
    }

    @PatchMapping
    public ResponseEntity<TransferMaterialResponse> updateTransferMaterials(@RequestBody UpdateTransferMaterialRequest request){
        return ResponseEntity.ok(updateTransferMaterial.execute(request));
    }

    @DeleteMapping("/{id}")
    public TransferMaterialResponse delete(@PathVariable Long id){
        return deleteTransferMaterial.execute(id);
    }

    @GetMapping("/totalTransfersTo/{clientId}")
    public List<String[]> getTotalTransfersTo(@PathVariable Long clientId){
        return transferMaterialRepository.getTransferMaterialsTo(clientId);
    }

    @GetMapping("/totalTransfersFrom/{clientId}")
    public List<String[]> getTotalTransfersFrom(@PathVariable Long clientId){
        return transferMaterialRepository.getTransferMaterialsFrom(clientId);
    }

    @GetMapping("/transferReport/{exportType}/{id}/{start}/{end}/{type}")
    public ResponseEntity<String> getTransferReport(@PathVariable Long id,
                                                             @PathVariable LocalDate start,
                                                             @PathVariable LocalDate end,
                                                             @PathVariable String exportType,
                                                             @PathVariable TransportationType type){

        final List<TransferMaterialsEntity> transferMaterials = transferMaterialRepository.getTransferMaterials(id,
                LocalDateTime.of(start, LocalTime.parse("00:00:00")),
                LocalDateTime.of(end, LocalTime.parse("23:59:59")),
                type);

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        params.put("dateSpan", LocalDate.from(start)+"-"+LocalDate.from(end));
        params.put("clientName", gasStationRepository.findById(id).orElseThrow().getName());

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(transferMaterials);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/transferReport.jrxml").getInputStream());
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
