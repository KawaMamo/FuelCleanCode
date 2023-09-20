package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {
    private Long id;
    private Long priceCategoryId;
    private Long materialId;
    private Money price;
}
