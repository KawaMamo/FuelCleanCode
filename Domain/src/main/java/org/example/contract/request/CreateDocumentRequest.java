package org.example.contract.request;

import lombok.Data;

@Data
public class CreateDocumentRequest {
    private String type;
    private String fileName;
    private byte[] content;
}
