package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.DeletedEntity;
import org.example.model.Deleted;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-26T00:06:40+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class DeletedMapperImpl implements DeletedMapper {

    @Override
    public DeletedEntity domainToEntity(Deleted deleted) {
        if ( deleted == null ) {
            return null;
        }

        DeletedEntity deletedEntity = new DeletedEntity();

        deletedEntity.setId( deleted.getId() );
        deletedEntity.setClassName( deleted.getClassName() );
        deletedEntity.setJsonValue( deleted.getJsonValue() );
        deletedEntity.setCreatedAt( deleted.getCreatedAt() );

        return deletedEntity;
    }

    @Override
    public Deleted entityToDomain(DeletedEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Deleted deleted = new Deleted();

        deleted.setId( entity.getId() );
        deleted.setJsonValue( entity.getJsonValue() );
        deleted.setCreatedAt( entity.getCreatedAt() );
        deleted.setClassName( entity.getClassName() );

        return deleted;
    }
}
