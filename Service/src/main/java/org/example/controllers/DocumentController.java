package org.example.controllers;

import org.example.contract.request.CreateDocumentRequest;
import org.example.contract.request.update.UpdateDocumentRequest;
import org.example.contract.response.DocumentResponse;
import org.example.entities.DocumentEntity;
import org.example.repositories.DocumentRepository;
import org.example.specifications.CriteriaArrayToList;
import org.example.specifications.FilterSpecifications;
import org.example.specifications.SearchCriteria;
import org.example.specifications.SearchFilter;
import org.example.useCases.CreateDocument;
import org.example.useCases.update.UpdateDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/document")
public class DocumentController {

    private final UpdateDocument updateDocument;
    private final CreateDocument createDocument;
    private final DocumentRepository documentRepository;
    private final PagedResourcesAssembler assembler;

    public DocumentController(UpdateDocument updateDocument,
                              CreateDocument createDocument,
                              DocumentRepository documentRepository, PagedResourcesAssembler assembler) {
        this.updateDocument = updateDocument;
        this.createDocument = createDocument;
        this.documentRepository = documentRepository;
        this.assembler = assembler;
    }

    @PostMapping
    public DocumentResponse createDocument(@RequestBody CreateDocumentRequest request){
        return createDocument.execute(request);
    }

    @GetMapping
    public PagedModel<DocumentResponse> listDocuments(SearchFilter filter, Pageable pageable){
        final List<SearchCriteria> criteriaList = CriteriaArrayToList.getCriteriaList(filter);
        final FilterSpecifications<DocumentEntity> specifications = new FilterSpecifications<>(criteriaList);
        final Page<DocumentEntity> page = documentRepository.findAll(specifications, pageable);
        return assembler.toModel(page);
    }

    @PatchMapping
    public ResponseEntity<DocumentResponse> update(@RequestBody UpdateDocumentRequest request){
        return ResponseEntity.ok(updateDocument.execute(request));
    }

}
