package org.example.useCases.update;


import org.example.contract.repository.DocumentRepo;
import org.example.contract.repository.TransRepo;
import org.example.contract.request.create.CreateDocumentRequest;
import org.example.contract.request.update.UpdateTransRequest;
import org.example.contract.response.TransResponse;
import org.example.mappers.DomainTransMapper;
import org.example.model.Document;
import org.example.model.Transportation;
import org.example.validators.update.UpdateTransValidator;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

public class UpdateTrans {
    public final UpdateTransValidator validator;
    private final DomainTransMapper mapper;
    private final TransRepo transRepo;
    private final DocumentRepo documentRepo;

    public UpdateTrans(UpdateTransValidator validator, DomainTransMapper mapper, TransRepo transRepo, DocumentRepo documentRepo) {
        this.validator = validator;
        this.mapper = mapper;
        this.transRepo = transRepo;
        this.documentRepo = documentRepo;
    }

    public TransResponse execute(UpdateTransRequest request){
        final Transportation original = transRepo.findById(request.getId()).orElseThrow(NoSuchElementException::new);
        validator.validate(request);
        final Transportation transportation = mapper.toDomain(request);
        transportation.setCreatedAt(original.getCreatedAt());
        transportation.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Transportation save = transRepo.save(transportation);
        return mapper.toResponse(save);
    }

    public TransResponse addDocument(Long id, CreateDocumentRequest request){
        final Transportation transportation = transRepo.findByIdAndDeletedAt(id, null).orElseThrow(NoSuchElementException::new);
        final Document document = new Document();
        document.setType(request.getType());
        document.setUrl(request.getFileName());
        document.setContent(request.getContent());
        document.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        transportation.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        final Document savedDocument = documentRepo.save(document);
        final List<Document> documentList = transportation.getDocument();
        documentList.add(savedDocument);
        transportation.setDocument(documentList);
        final Transportation save = transRepo.save(transportation);
        return mapper.toResponse(save);
    }
}
