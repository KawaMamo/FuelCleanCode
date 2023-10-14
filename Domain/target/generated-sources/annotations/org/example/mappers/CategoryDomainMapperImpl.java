package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.contract.request.create.CreateCategoryRequest;
import org.example.contract.request.update.UpdateCategoryRequest;
import org.example.contract.response.CategoryResponse;
import org.example.model.Category;
import org.example.model.Material;
import org.example.model.PriceCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-14T21:53:03+0300",
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
        categoryResponse.setUpdatedAt( category.getUpdatedAt() );

        return categoryResponse;
    }

    @Override
    public Category requestToDomain(UpdateCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category category = new Category();

        category.setPriceCategory( updateCategoryRequestToPriceCategory( request ) );
        category.setMaterial( updateCategoryRequestToMaterial( request ) );
        category.setId( request.getId() );
        category.setPrice( request.getPrice() );

        return category;
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

    protected PriceCategory updateCategoryRequestToPriceCategory(UpdateCategoryRequest updateCategoryRequest) {
        if ( updateCategoryRequest == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( updateCategoryRequest.getPriceCategoryId() );

        return priceCategory;
    }

    protected Material updateCategoryRequestToMaterial(UpdateCategoryRequest updateCategoryRequest) {
        if ( updateCategoryRequest == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( updateCategoryRequest.getMaterialId() );

        return material;
    }
}
