package org.example.useCases.update;

import org.example.contract.repository.DocumentRepo;
import org.example.contract.request.update.UpdateDocumentRequest;
import org.example.contract.response.DocumentResponse;
import org.example.mappers.DocumentDomainMapper;
import org.example.model.Document;
import org.example.validators.update.UpdateDocumentValidator;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class UpdateDocument {
    private final DocumentRepo documentRepo;
    private final DocumentDomainMapper documentDomainMapper;
    private final UpdateDocumentValidator validator;

    public UpdateDocument(DocumentRepo documentRepo,
                          DocumentDomainMapper documentDomainMapper,
                          UpdateDocumentValidator validator) {
        this.documentRepo = documentRepo;
        this.documentDomainMapper = documentDomainMapper;
        this.validator = validator;
    }

    public DocumentResponse execute(UpdateDocumentRequest request){
        final Document originalDocument = documentRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Document document = documentDomainMapper.requestToDomain(request);
        document.setCreatedAt(originalDocument.getCreatedAt());
        document.setUpdatedAt(LocalDateTime.now());
        final Document save = documentRepo.save(document);
        return documentDomainMapper.domainToResponse(save);
    }
}
