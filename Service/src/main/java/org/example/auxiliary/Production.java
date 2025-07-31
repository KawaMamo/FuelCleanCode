package org.example.auxiliary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Production {
    Integer amountSum;
    String materialName;
    String refineryName;
}
