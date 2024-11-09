package org.example.auxiliary;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entities.TransLogEntity;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransLogAggregated {
    public TransLogEntity transLogEntity;
    public Double sum;
    public Long count;
    public Double forfeitSum;
    public String forfeitCurrency;
}
