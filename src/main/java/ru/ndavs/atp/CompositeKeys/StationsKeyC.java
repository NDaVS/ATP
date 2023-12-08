package ru.ndavs.atp.CompositeKeys;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class StationsKeyC implements Serializable {
    private Long station_1C;
    private Long station_2C;
}
