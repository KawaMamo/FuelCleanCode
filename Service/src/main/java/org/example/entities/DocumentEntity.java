package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class DocumentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String url;
    private String type;
    private Long resourceId;
    @Lob
    @Column(name = "content", columnDefinition = "LONGBLOB")
    private byte[] content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
