package org.example.controllers;

import org.example.contract.request.create.CreateTransferMaterialRequest;
import org.example.contract.request.update.UpdateTransferMaterialRequest;
import org.example.contract.response.TransferMaterialResponse;
import org.example.entities.TransferMaterialsEntity;
import org.example.mappers.TransferMaterialsMapper;
import org.example.model.TransferMaterials;
import org.example.repositories.TransferMaterialRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.create.CreateTransferMaterial;
import org.example.useCases.delete.DeleteTransferMaterial;
import org.example.useCases.update.UpdateTransferMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/transfer-materials")
public class TransferMaterialController {

    private final CreateTransferMaterial createTransferMaterial;
    private final TransferMaterialRepository transferMaterialRepository;
    private final TransferMaterialsMapper transferMaterialsMapper;
    private final PagedResourcesAssembler assembler;
    private final UpdateTransferMaterial updateTransferMaterial;
    private final DeleteTransferMaterial deleteTransferMaterial;

    public TransferMaterialController(CreateTransferMaterial createTransferMaterial,
                                      TransferMaterialRepository transferMaterialRepository,
                                      TransferMaterialsMapper transferMaterialsMapper,
                                      PagedResourcesAssembler assembler, UpdateTransferMaterial updateTransferMaterial, DeleteTransferMaterial deleteTransferMaterial) {
        this.createTransferMaterial = createTransferMaterial;
        this.transferMaterialRepository = transferMaterialRepository;
        this.transferMaterialsMapper = transferMaterialsMapper;
        this.assembler = assembler;
        this.updateTransferMaterial = updateTransferMaterial;
        this.deleteTransferMaterial = deleteTransferMaterial;
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
}
