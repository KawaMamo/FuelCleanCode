package org.example.mappers;

import org.example.contract.request.CreateDocumentRequest;
import org.example.contract.response.DocumentResponse;
import org.example.model.Document;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentDomainMapper {
    Document requestToDomain(CreateDocumentRequest request);
    DocumentResponse domainToResponse(Document document);
}
