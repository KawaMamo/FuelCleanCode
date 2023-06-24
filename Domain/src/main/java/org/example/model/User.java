package org.example.model;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class User {
    private Long Id;
    private Person person;
    private AccountType accountType;
    private Document digitalSignature;
    private String branch;
    private Boolean isActivated;
    private byte[] fingerprint;
    private LocalDateTime createdAt;
}
