package org.example.mappers;

import org.example.entities.DocumentEntity;
import org.example.model.Document;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentMapper {

    DocumentEntity toEntity(Document document);
    Document toDomain(DocumentEntity document);
}
