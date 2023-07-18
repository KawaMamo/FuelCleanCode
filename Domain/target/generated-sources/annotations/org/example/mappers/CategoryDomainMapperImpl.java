package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.CreateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.model.Category;
import org.example.model.Material;
import org.example.model.PriceCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-18T20:15:10+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class CategoryDomainMapperImpl implements CategoryDomainMapper {

    @Override
    public Category requestToDomain(CreateCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category category = new Category();

        category.setPriceCategory( createCategoryRequestToPriceCategory( request ) );
        category.setMaterial( createCategoryRequestToMaterial( request ) );
        category.setPrice( request.getPrice() );

        return category;
    }

    @Override
    public CategoryResponse domainToResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse categoryResponse = new CategoryResponse();

        categoryResponse.setId( category.getId() );
        categoryResponse.setPriceCategory( category.getPriceCategory() );
        categoryResponse.setMaterial( category.getMaterial() );
        categoryResponse.setPrice( category.getPrice() );
        categoryResponse.setCreatedAt( category.getCreatedAt() );

        return categoryResponse;
    }

    protected PriceCategory createCategoryRequestToPriceCategory(CreateCategoryRequest createCategoryRequest) {
        if ( createCategoryRequest == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( createCategoryRequest.getPriceCategoryId() );

        return priceCategory;
    }

    protected Material createCategoryRequestToMaterial(CreateCategoryRequest createCategoryRequest) {
        if ( createCategoryRequest == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( createCategoryRequest.getMaterialId() );

        return material;
    }
}
