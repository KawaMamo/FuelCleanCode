package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreatePriceCategoryRequest;
import org.example.contract.request.update.UpdatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.model.PriceCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-27T18:34:59+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class PriceCategoryDomainMapperImpl implements PriceCategoryDomainMapper {

    @Override
    public PriceCategory requestToDomain(CreatePriceCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setName( request.getName() );

        return priceCategory;
    }

    @Override
    public PriceCategoryResponse domainToResponse(PriceCategory priceCategory) {
        if ( priceCategory == null ) {
            return null;
        }

        PriceCategoryResponse priceCategoryResponse = new PriceCategoryResponse();

        priceCategoryResponse.setId( priceCategory.getId() );
        priceCategoryResponse.setName( priceCategory.getName() );
        priceCategoryResponse.setCreatedAt( priceCategory.getCreatedAt() );
        priceCategoryResponse.setUpdatedAt( priceCategory.getUpdatedAt() );

        return priceCategoryResponse;
    }

    @Override
    public PriceCategory requestToDomain(UpdatePriceCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( request.getId() );
        priceCategory.setName( request.getName() );

        return priceCategory;
    }
}
