package org.example.controllers;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
