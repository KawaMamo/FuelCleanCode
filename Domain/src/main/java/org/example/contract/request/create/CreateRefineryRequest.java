package org.example.contract.request.create;

import lombok.Data;
import org.example.contract.request.Request;

@Data
public class CreateRefineryRequest implements Request {
    private String name;
}
