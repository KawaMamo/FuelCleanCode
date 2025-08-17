package org.example.mappers;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateDocumentRequest;
import org.example.contract.request.update.UpdateDocumentRequest;
import org.example.contract.response.DocumentResponse;
import org.example.model.Document;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-17T17:16:28+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class DocumentDomainMapperImpl implements DocumentDomainMapper {

    @Override
    public Document requestToDomain(CreateDocumentRequest request) {
        if ( request == null ) {
            return null;
        }

        Document document = new Document();

        document.setType( request.getType() );
        byte[] content = request.getContent();
        if ( content != null ) {
            document.setContent( Arrays.copyOf( content, content.length ) );
        }

        return document;
    }

    @Override
    public DocumentResponse domainToResponse(Document document) {
        if ( document == null ) {
            return null;
        }

        DocumentResponse documentResponse = new DocumentResponse();

        documentResponse.setId( document.getId() );
        documentResponse.setUrl( document.getUrl() );
        documentResponse.setType( document.getType() );
        documentResponse.setResourceId( document.getResourceId() );
        byte[] content = document.getContent();
        if ( content != null ) {
            documentResponse.setContent( Arrays.copyOf( content, content.length ) );
        }
        documentResponse.setCreatedAt( document.getCreatedAt() );
        documentResponse.setUpdatedAt( document.getUpdatedAt() );

        return documentResponse;
    }

    @Override
    public Document requestToDomain(UpdateDocumentRequest request) {
        if ( request == null ) {
            return null;
        }

        Document document = new Document();

        document.setId( request.getId() );
        document.setType( request.getType() );
        byte[] content = request.getContent();
        if ( content != null ) {
            document.setContent( Arrays.copyOf( content, content.length ) );
        }

        return document;
    }
}
