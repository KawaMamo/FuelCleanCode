package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreatePriceCategoryRequest;
import org.example.contract.response.PriceCategoryResponse;
import org.example.model.PriceCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T16:54:24+0300",
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

        return priceCategoryResponse;
    }
}
