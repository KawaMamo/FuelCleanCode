package org.example.controllers;

import org.example.contract.request.create.CreateMaterialRequest;
import org.example.contract.request.update.UpdateMaterialRequest;
import org.example.contract.response.MaterialResponse;
import org.example.entities.MaterialEntity;
import org.example.mappers.MaterialMapper;
import org.example.model.Material;
import org.example.repositories.MaterialRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateMaterial;
import org.example.useCases.delete.DeleteMaterial;
import org.example.useCases.update.UpdateMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/material")
public class MaterialController {

    private final CreateMaterial createMaterial;
    private final MaterialRepository materialRepository;
    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final MaterialMapper materialMapper;
    private final UpdateMaterial updateMaterial;
    private final DeleteMaterial deleteMaterial;

    public MaterialController(CreateMaterial createMaterial,
                              MaterialRepository materialRepository,
                              PagedResourcesAssembler pagedResourcesAssembler,
                              MaterialMapper materialMapper,
                              UpdateMaterial updateMaterial, DeleteMaterial deleteMaterial) {
        this.createMaterial = createMaterial;
        this.materialRepository = materialRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
        this.materialMapper = materialMapper;
        this.updateMaterial = updateMaterial;
        this.deleteMaterial = deleteMaterial;
    }

    @PostMapping
    MaterialResponse createMaterial(CreateMaterialRequest request){
        return createMaterial.execute(request);
    }

    @GetMapping
    PagedModel<MaterialResponse> listMaterials(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<MaterialEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<Material> page = materialRepository.findAll(specifications, pageable).map(materialMapper::entityToDomain);
        return pagedResourcesAssembler.toModel(page);
    }

    @PatchMapping
    ResponseEntity<MaterialResponse> updateMaterial(@RequestBody UpdateMaterialRequest request){
        return ResponseEntity.ok(updateMaterial.execute(request));
    }
    @DeleteMapping("/{id}")
    private MaterialResponse delete(@PathVariable Long id){
        return deleteMaterial.execute(id);
    }
}
