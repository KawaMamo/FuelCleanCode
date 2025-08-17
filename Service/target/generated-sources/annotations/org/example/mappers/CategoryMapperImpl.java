package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.CategoryEntity;
import org.example.entities.MaterialEntity;
import org.example.entities.PriceCategoryEntity;
import org.example.model.Category;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.PriceCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-17T17:16:35+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity domainToEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setPriceCurrency( categoryPriceCurrency( category ) );
        categoryEntity.setPriceAmount( categoryPriceAmount( category ) );
        categoryEntity.setId( category.getId() );
        categoryEntity.setPriceCategory( priceCategoryToPriceCategoryEntity( category.getPriceCategory() ) );
        categoryEntity.setMaterial( materialToMaterialEntity( category.getMaterial() ) );
        categoryEntity.setCreatedAt( category.getCreatedAt() );
        categoryEntity.setUpdatedAt( category.getUpdatedAt() );

        return categoryEntity;
    }

    @Override
    public Category entityToDomain(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Category category = new Category();

        category.setPrice( categoryEntityToMoney( categoryEntity ) );
        category.setId( categoryEntity.getId() );
        category.setPriceCategory( priceCategoryEntityToPriceCategory( categoryEntity.getPriceCategory() ) );
        category.setMaterial( materialEntityToMaterial( categoryEntity.getMaterial() ) );
        category.setCreatedAt( categoryEntity.getCreatedAt() );
        category.setUpdatedAt( categoryEntity.getUpdatedAt() );

        return category;
    }

    private String categoryPriceCurrency(Category category) {
        if ( category == null ) {
            return null;
        }
        Money price = category.getPrice();
        if ( price == null ) {
            return null;
        }
        String currency = price.getCurrency();
        if ( currency == null ) {
            return null;
        }
        return currency;
    }

    private Double categoryPriceAmount(Category category) {
        if ( category == null ) {
            return null;
        }
        Money price = category.getPrice();
        if ( price == null ) {
            return null;
        }
        Double amount = price.getAmount();
        if ( amount == null ) {
            return null;
        }
        return amount;
    }

    protected PriceCategoryEntity priceCategoryToPriceCategoryEntity(PriceCategory priceCategory) {
        if ( priceCategory == null ) {
            return null;
        }

        PriceCategoryEntity priceCategoryEntity = new PriceCategoryEntity();

        priceCategoryEntity.setId( priceCategory.getId() );
        priceCategoryEntity.setName( priceCategory.getName() );
        priceCategoryEntity.setCreatedAt( priceCategory.getCreatedAt() );
        priceCategoryEntity.setUpdatedAt( priceCategory.getUpdatedAt() );

        return priceCategoryEntity;
    }

    protected MaterialEntity materialToMaterialEntity(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialEntity materialEntity = new MaterialEntity();

        materialEntity.setId( material.getId() );
        materialEntity.setName( material.getName() );
        materialEntity.setCreatedAt( material.getCreatedAt() );
        materialEntity.setUpdatedAt( material.getUpdatedAt() );

        return materialEntity;
    }

    protected Money categoryEntityToMoney(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Money money = new Money();

        money.setCurrency( categoryEntity.getPriceCurrency() );
        money.setAmount( categoryEntity.getPriceAmount() );

        return money;
    }

    protected PriceCategory priceCategoryEntityToPriceCategory(PriceCategoryEntity priceCategoryEntity) {
        if ( priceCategoryEntity == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( priceCategoryEntity.getId() );
        priceCategory.setName( priceCategoryEntity.getName() );
        priceCategory.setCreatedAt( priceCategoryEntity.getCreatedAt() );
        priceCategory.setUpdatedAt( priceCategoryEntity.getUpdatedAt() );

        return priceCategory;
    }

    protected Material materialEntityToMaterial(MaterialEntity materialEntity) {
        if ( materialEntity == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( materialEntity.getId() );
        material.setName( materialEntity.getName() );
        material.setCreatedAt( materialEntity.getCreatedAt() );
        material.setUpdatedAt( materialEntity.getUpdatedAt() );

        return material;
    }
}
