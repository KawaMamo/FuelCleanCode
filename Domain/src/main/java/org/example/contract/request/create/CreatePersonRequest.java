package org.example.contract.request.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.contract.request.Request;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonRequest implements Request {

    private String name;
    private String father;
    private String mother;
    private String nationalId;
    private String birthPlace;
    private LocalDate birthDate;
}
