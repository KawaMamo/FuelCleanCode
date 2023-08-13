package org.example.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class Person {
    private Long id;
    private String name;
    private String father;
    private String mother;
    private String nationalId;
    private String birthPlace;
    private LocalDate birthDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
