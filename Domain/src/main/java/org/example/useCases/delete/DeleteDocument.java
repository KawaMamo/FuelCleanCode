package org.example.useCases.delete;

import org.example.contract.repository.DocumentRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.response.DocumentResponse;
import org.example.mappers.DocumentDomainMapper;
import org.example.model.Document;

import java.util.NoSuchElementException;

public class DeleteDocument {
    private final DocumentDomainMapper mapper;
    private final DocumentRepo documentRepo;
    public DeleteDocument(DocumentDomainMapper mapper, DocumentRepo documentRepo) {
        this.mapper = mapper;
        this.documentRepo = documentRepo;
    }
    public DocumentResponse execute(Long id) {
        Document document = documentRepo.findById(id).orElseThrow(NoSuchElementException::new);
        documentRepo.delete(document);
        return mapper.domainToResponse(document);
    }
}
