package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    private Long priceCategoryId;
    private Long materialId;
    private Money price;
}
