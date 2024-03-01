package org.example.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@EqualsAndHashCode(callSuper = true)
@Data
public class GasStation extends Place{


    private PriceCategory priceCategory;
    private Region region;
    private Person owner;
    private Group group;
    private Material material;
}
