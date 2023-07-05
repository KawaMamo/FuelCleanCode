package org.example.contract.request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class CreatePersonRequest implements Request{

    private String name;
    private String father;
    private String mother;
    private String nationalId;
    private String birthPlace;
    private LocalDate birthDate;
}
