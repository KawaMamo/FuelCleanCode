package org.example.mappers;

import javax.annotation.processing.Generated;
import org.example.entities.CategoryEntity;
import org.example.model.Category;
import org.example.model.Material;
import org.example.model.Money;
import org.example.model.PriceCategory;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-13T01:12:22+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity domainToEntity(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setPriceCategoryId( categoryPriceCategoryId( category ) );
        categoryEntity.setMaterialId( categoryMaterialId( category ) );
        categoryEntity.setPriceCurrency( categoryPriceCurrency( category ) );
        categoryEntity.setPriceAmount( categoryPriceAmount( category ) );
        categoryEntity.setId( category.getId() );
        categoryEntity.setCreatedAt( category.getCreatedAt() );

        return categoryEntity;
    }

    @Override
    public Category entityToDomain(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Category category = new Category();

        category.setPriceCategory( categoryEntityToPriceCategory( categoryEntity ) );
        category.setMaterial( categoryEntityToMaterial( categoryEntity ) );
        category.setPrice( categoryEntityToMoney( categoryEntity ) );
        category.setId( categoryEntity.getId() );
        category.setCreatedAt( categoryEntity.getCreatedAt() );

        return category;
    }

    private Long categoryPriceCategoryId(Category category) {
        if ( category == null ) {
            return null;
        }
        PriceCategory priceCategory = category.getPriceCategory();
        if ( priceCategory == null ) {
            return null;
        }
        Long id = priceCategory.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long categoryMaterialId(Category category) {
        if ( category == null ) {
            return null;
        }
        Material material = category.getMaterial();
        if ( material == null ) {
            return null;
        }
        Long id = material.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    protected PriceCategory categoryEntityToPriceCategory(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        PriceCategory priceCategory = new PriceCategory();

        priceCategory.setId( categoryEntity.getPriceCategoryId() );

        return priceCategory;
    }

    protected Material categoryEntityToMaterial(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( categoryEntity.getMaterialId() );

        return material;
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
}
