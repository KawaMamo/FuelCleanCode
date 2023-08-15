package org.example.adapters;

import org.example.contract.repository.DocumentRepo;
import org.example.entities.DocumentEntity;
import org.example.mappers.DocumentMapper;
import org.example.model.Document;
import org.example.repositories.DocumentRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

public class DocumentAdapter implements DocumentRepo {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;

    public DocumentAdapter(DocumentRepository repository, DocumentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Document save(Document document) {
        final DocumentEntity documentEntity = mapper.toEntity(document);
        final DocumentEntity save = repository.save(documentEntity);
        return mapper.toDomain(save);
    }

    @Override
    public Optional<Document> findById(Long id) {
        final DocumentEntity documentEntity = repository.findById(id).orElseThrow(NoSuchElementException::new);
        return Optional.ofNullable(mapper.toDomain(documentEntity));
    }

    @Override
    public void delete(Document document) {
        repository.delete(mapper.toEntity(document));
    }
}
