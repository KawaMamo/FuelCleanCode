package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Office {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

}
