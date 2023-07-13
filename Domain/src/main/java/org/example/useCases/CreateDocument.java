package org.example.useCases;

import org.example.contract.repository.DocumentRepo;
import org.example.contract.request.CreateDocumentRequest;
import org.example.contract.response.DocumentResponse;
import org.example.mappers.DocumentDomainMapper;
import org.example.model.Document;
import org.example.validators.CreateDocumentValidator;

import java.time.LocalDateTime;

public class CreateDocument {
    private final CreateDocumentValidator validator;
    private final DocumentDomainMapper mapper;
    private final DocumentRepo documentRepo;


    public CreateDocument(CreateDocumentValidator validator, DocumentDomainMapper mapper, DocumentRepo documentRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.documentRepo = documentRepo;
    }

    public DocumentResponse execute(CreateDocumentRequest request){
        validator.validate(request);
        final Document document = mapper.requestToDomain(request);
        document.setCreatedAt(LocalDateTime.now());
        final Document save = documentRepo.save(document);
        return mapper.domainToResponse(save);
    }
}
