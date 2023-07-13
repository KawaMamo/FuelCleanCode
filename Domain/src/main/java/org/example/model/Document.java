package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Document {
    private Long id;
    private String url;
    private String type;
    private Long resourceId;
    private byte[] content;
    private LocalDateTime createdAt;
}
