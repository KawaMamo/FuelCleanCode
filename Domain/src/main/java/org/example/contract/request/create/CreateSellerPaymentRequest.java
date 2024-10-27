package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSellerPaymentRequest {
    private Money amount;
    private Long billNumber;
    private LocalDateTime createdAt;
    private String notes;
    private Long sellerId;
}
