package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.contract.request.Request;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRefineryRequest implements Request {
    private String name;
}
