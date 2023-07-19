package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.PriceCategoryEntity;
import org.example.model.PriceCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-19T22:46:30+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class PriceCategoryMapperImpl implements PriceCategoryMapper {

    @Override
    public PriceCategoryEntity domainToEntity(PriceCategory priceCategory) {
        if ( priceCategory == null ) {
            return null;
        }

        PriceCategoryEntity priceCategoryEntity = new PriceCategoryEntity();

        priceCategoryEntity.setId( priceCategory.getId() );
        priceCategoryEntity.setName( priceCategory.getName() );
        priceCategoryEntity.setCreatedAt( priceCategory.getCreatedAt() );

        return priceCategoryEntity;
    }

    @Override
    public PriceCategory entityToDomain(PriceCategoryEntity priceCategory) {
        if ( priceCategory == null ) {
            return null;
        }

        PriceCategory priceCategory1 = new PriceCategory();

        priceCategory1.setId( priceCategory.getId() );
        priceCategory1.setName( priceCategory.getName() );
        priceCategory1.setCreatedAt( priceCategory.getCreatedAt() );

        return priceCategory1;
    }
}
