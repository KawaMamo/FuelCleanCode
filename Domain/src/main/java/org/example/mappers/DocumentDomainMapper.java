package org.example.mappers;

import org.example.contract.request.create.CreateDocumentRequest;
import org.example.contract.request.update.UpdateDocumentRequest;
import org.example.contract.response.DocumentResponse;
import org.example.model.Document;
import org.mapstruct.Mapper;

@Mapper
public interface DocumentDomainMapper {
    Document requestToDomain(CreateDocumentRequest request);
    DocumentResponse domainToResponse(Document document);
    Document requestToDomain(UpdateDocumentRequest request);
}
