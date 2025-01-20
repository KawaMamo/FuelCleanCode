package org.example.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.example.contract.request.create.CreateClientPaymentRequest;
import org.example.contract.request.update.UpdateClientPaymentRequest;
import org.example.contract.response.ClientPaymentResponse;
import org.example.entities.ClientPayment;
import org.example.entities.TransportationType;
import org.example.mappers.ClientPaymentMapper;
import org.example.model.Money;
import org.example.repositories.ClientPaymentRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateClientPayment;
import org.example.useCases.delete.DeleteClientPayment;
import org.example.useCases.update.UpdateClientPayment;
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
@RequestMapping("api/v1/client-payment")
public class ClientPaymentController {

    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final CreateClientPayment createClientPayment;
    private final UpdateClientPayment updateClientPayment;
    private final DeleteClientPayment deleteClientPayment;
    private final ClientPaymentMapper mapper;
    private final ClientPaymentRepository repository;

    public ClientPaymentController(PagedResourcesAssembler pagedResourcesAssembler,
                                   CreateClientPayment createClientPayment,
                                   UpdateClientPayment updateClientPayment,
                                   DeleteClientPayment deleteClientPayment,
                                   ClientPaymentMapper mapper,
                                   ClientPaymentRepository repository) {
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.createClientPayment = createClientPayment;
        this.updateClientPayment = updateClientPayment;
        this.deleteClientPayment = deleteClientPayment;
        this.mapper = mapper;
        this.repository = repository;
    }

    @PostMapping
    public ClientPaymentResponse create(@RequestBody CreateClientPaymentRequest request){
        return createClientPayment.create(request);
    }

    @PatchMapping
    public ResponseEntity<ClientPaymentResponse> update(@RequestBody UpdateClientPaymentRequest request){
        return ResponseEntity.ok(updateClientPayment.update(request));
    }

    @DeleteMapping("/{id}")
    public ClientPaymentResponse delete(@PathVariable Long id){return deleteClientPayment.delete(id);}

    @GetMapping
    PagedModel<ClientPaymentResponse> get(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<ClientPayment> specifications = new FilterSpecifications<>(criteriaList);
        final Page<org.example.model.ClientPayment> page = repository.findAll(specifications, pageable).map(mapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<org.example.model.ClientPayment> getAll(){
        return repository.findAll().stream().map(mapper::entityToDomain).toList();
    }

    @GetMapping("/totalPayments/{gasStationId}")
    public List<String[]> getTotalPayments(@PathVariable Long gasStationId){
        return repository.getTotalPayments(gasStationId);
    }

    @GetMapping("/clientsPayments/{exportType}/{id}/{start}/{end}")
    public ResponseEntity<String> getClientPayments(@PathVariable Long id,
                                                             @PathVariable LocalDate start,
                                                             @PathVariable LocalDate end,
                                                             @PathVariable String exportType){
        final List<ClientPayment> paymentsList = repository.getPaymentsList(id,
                LocalDateTime.of(start, LocalTime.parse("00:00:00")),
                LocalDateTime.of(end, LocalTime.parse("23:59:59")));

        Map<String, Object> params = new HashMap<>();
        params.put("nowLocalDT", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        params.put("dateSpan", LocalDate.from(start)+"-"+LocalDate.from(end));
        params.put("clientName", paymentsList.get(0).getGasStation().getName());

        try {
            final JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(paymentsList);
            JasperReport regionReport = JasperCompileManager.compileReport(new ClassPathResource("templates/clientPayments.jrxml").getInputStream());
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
