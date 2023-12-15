package ru.ndavs.atp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class BusSpecs {
    @Id
    private String model;
    private Integer number_of_sits;
}
