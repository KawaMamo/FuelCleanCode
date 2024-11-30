package org.example.mappers;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import org.example.entities.DocumentEntity;
import org.example.model.Document;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-30T19:14:30+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public DocumentEntity toEntity(Document document) {
        if ( document == null ) {
            return null;
        }

        DocumentEntity documentEntity = new DocumentEntity();

        documentEntity.setId( document.getId() );
        documentEntity.setUrl( document.getUrl() );
        documentEntity.setType( document.getType() );
        documentEntity.setResourceId( document.getResourceId() );
        byte[] content = document.getContent();
        if ( content != null ) {
            documentEntity.setContent( Arrays.copyOf( content, content.length ) );
        }
        documentEntity.setCreatedAt( document.getCreatedAt() );
        documentEntity.setUpdatedAt( document.getUpdatedAt() );

        return documentEntity;
    }

    @Override
    public Document toDomain(DocumentEntity document) {
        if ( document == null ) {
            return null;
        }

        Document document1 = new Document();

        document1.setId( document.getId() );
        document1.setUrl( document.getUrl() );
        document1.setType( document.getType() );
        document1.setResourceId( document.getResourceId() );
        byte[] content = document.getContent();
        if ( content != null ) {
            document1.setContent( Arrays.copyOf( content, content.length ) );
        }
        document1.setCreatedAt( document.getCreatedAt() );
        document1.setUpdatedAt( document.getUpdatedAt() );

        return document1;
    }
}
