package org.example.contract.request.update;

import lombok.Data;

@Data
public class UpdateDocumentRequest {
    private Long id;
    private String type;
    private String fileName;
    private byte[] content;
}
