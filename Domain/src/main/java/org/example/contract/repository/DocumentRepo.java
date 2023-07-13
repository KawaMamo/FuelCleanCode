package org.example.contract.repository;

import org.example.model.Document;

import java.util.Optional;

public interface DocumentRepo {
    Document save(Document document);
    Optional<Document> findById(Long id);
}
