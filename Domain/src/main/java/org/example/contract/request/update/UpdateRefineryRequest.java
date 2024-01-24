package org.example.contract.request.update;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRefineryRequest {
    private Long id;
    private String name;
    private String translation;
    private Long regionId;
}
