package org.example.repositories.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class RefineryEntity extends Place {


    private String name;
    private LocalDateTime createdAt;
}
