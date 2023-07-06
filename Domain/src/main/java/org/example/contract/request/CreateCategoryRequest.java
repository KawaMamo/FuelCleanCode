package org.example.contract.request;

import lombok.Data;
import org.example.model.Money;

@Data
public class CreateCategoryRequest {

    private Long priceCategoryId;
    private Long materialId;
    private Money price;
}
