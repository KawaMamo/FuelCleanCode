package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Money;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSellerPaymentRequest {
    private Long id;
    private Money amount;
    private Long billNumber;
    private String notes;
    private Long sellerId;
}
