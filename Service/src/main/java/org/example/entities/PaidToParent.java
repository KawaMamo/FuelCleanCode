package org.example.entities;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class PaidToParent extends Payment {

}
