package org.example.contract.request.update;

import lombok.Data;
import org.example.model.Money;
@Data
public class UpdateCategoryRequest {
    private Long id;
    private Long priceCategoryId;
    private Long materialId;
    private Money price;
}
