package org.example.contract.request;

import lombok.Data;
import org.example.model.Money;

import java.time.LocalDateTime;
@Data
public class CreateCategoryRequest {

    private Long priceCategoryId;
    private Long materialId;
    private Money price;
    private LocalDateTime createdAt;
}
