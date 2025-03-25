package org.example.controllers;

import org.example.contract.request.create.CreateOfficeRequest;
import org.example.contract.request.update.UpdateOfficeRequest;
import org.example.contract.response.OfficeResponse;
import org.example.entities.OfficeEntity;
import org.example.mappers.OfficeMapper;
import org.example.model.Office;
import org.example.repositories.OfficeRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateOffice;
import org.example.useCases.delete.DeleteOffice;
import org.example.useCases.update.UpdateOffice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/v1/office")
public class OfficeController {

    private final CreateOffice createOffice;
    private final OfficeRepository officeRepository;
    private final OfficeMapper officeMapper;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final UpdateOffice updateOffice;
    private final DeleteOffice deleteOffice;

    public OfficeController(CreateOffice createOffice,
                            OfficeRepository officeRepository,
                            OfficeMapper officeMapper,
                            PagedResourcesAssembler pagedResourcesAssembler,
                            UpdateOffice updateOffice, DeleteOffice deleteOffice) {
        this.createOffice = createOffice;
        this.officeRepository = officeRepository;
        this.officeMapper = officeMapper;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.updateOffice = updateOffice;
        this.deleteOffice = deleteOffice;
    }
    @PostMapping
    public OfficeResponse createOffice(@RequestBody CreateOfficeRequest request){
        return createOffice.execute(request);
    }

    @GetMapping
    public PagedModel<OfficeResponse> listOffices(SearchFilter filter, Pageable pageable) {
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<OfficeEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Office> page = officeRepository.findAll(specifications, pageable).map(officeMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @GetMapping("/all")
    public List<Office> listAll(){
        return officeRepository.findAll().stream().map(officeMapper::entityToDomain).toList();
    }

    @PatchMapping
    ResponseEntity<OfficeResponse> updateOffice(@RequestBody UpdateOfficeRequest request){
        return ResponseEntity.ok(updateOffice.execute(request));
    }
    @DeleteMapping("/{id}")
    public OfficeResponse delete(@PathVariable Long id){
        return deleteOffice.execute(id);
    }
}
