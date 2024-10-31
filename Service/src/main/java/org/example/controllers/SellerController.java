package org.example.controllers;

import org.example.contract.request.create.CreateSellerRequest;
import org.example.contract.request.update.UpdateSellerRequest;
import org.example.contract.response.SellerResponse;
import org.example.entities.RegionEntity;
import org.example.mappers.SellerMapper;
import org.example.model.Region;
import org.example.model.Seller;
import org.example.repositories.SellerRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateSeller;
import org.example.useCases.delete.DeleteSeller;
import org.example.useCases.update.UpdateSeller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/seller")
public class SellerController {

    private final PagedResourcesAssembler assembler;
    private final SellerMapper mapper;
    private final SellerRepository repository;
    private final CreateSeller createSeller;
    private final UpdateSeller updateSeller;
    private final DeleteSeller deleteSeller;

    public SellerController(PagedResourcesAssembler assembler,
                            SellerMapper mapper,
                            SellerRepository repository,
                            CreateSeller createSeller,
                            UpdateSeller updateSeller,
                            DeleteSeller deleteSeller) {
        this.assembler = assembler;
        this.mapper = mapper;
        this.repository = repository;
        this.createSeller = createSeller;
        this.updateSeller = updateSeller;
        this.deleteSeller = deleteSeller;
    }

    @PostMapping
    public SellerResponse create(@RequestBody CreateSellerRequest request){
        return createSeller.execute(request);
    }

    @PatchMapping
    public ResponseEntity<SellerResponse> update(@RequestBody UpdateSellerRequest request){
        return ResponseEntity.ok(updateSeller.execute(request));
    }

    @DeleteMapping("/{id}")
    public SellerResponse delete(@PathVariable Long id){
        return deleteSeller.execute(id);
    }

    @GetMapping("/all")
    public List<Seller> getAll(){return repository.findAll().stream().map(mapper::entityToDomain).toList();}

    @GetMapping
    PagedModel<SellerResponse> listSellers(SearchFilter searchFilter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(searchFilter);
        final FilterSpecifications<org.example.entities.Seller> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Seller> page = repository.findAll(specifications, pageable).map(mapper::entityToDomain);
        return assembler.toModel(page);
    }
}
