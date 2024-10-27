package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.PaidToParent;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-27T16:55:27+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class PaidToParentMapperImpl implements PaidToParentMapper {

    @Override
    public PaidToParent domainToEntity(org.example.model.PaidToParent domain) {
        if ( domain == null ) {
            return null;
        }

        PaidToParent paidToParent = new PaidToParent();

        paidToParent.setId( domain.getId() );
        paidToParent.setBillNumber( domain.getBillNumber() );
        paidToParent.setCreatedAt( domain.getCreatedAt() );
        paidToParent.setNotes( domain.getNotes() );

        return paidToParent;
    }

    @Override
    public org.example.model.PaidToParent entityToDomain(PaidToParent entity) {
        if ( entity == null ) {
            return null;
        }

        org.example.model.PaidToParent paidToParent = new org.example.model.PaidToParent();

        paidToParent.setId( entity.getId() );
        paidToParent.setBillNumber( entity.getBillNumber() );
        paidToParent.setCreatedAt( entity.getCreatedAt() );
        paidToParent.setNotes( entity.getNotes() );

        return paidToParent;
    }
}
