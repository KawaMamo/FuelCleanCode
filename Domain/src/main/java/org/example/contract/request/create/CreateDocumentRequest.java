package org.example.contract.request.create;

import lombok.Data;

@Data
public class CreateDocumentRequest {
    private String type;
    private String fileName;
    private String content;
}
