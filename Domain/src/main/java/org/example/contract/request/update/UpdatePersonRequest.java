package org.example.contract.request.update;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UpdatePersonRequest {
    private Long id;
    private String name;
    private String father;
    private String mother;
    private String nationalId;
    private String birthPlace;
    private LocalDate birthDate;
}
