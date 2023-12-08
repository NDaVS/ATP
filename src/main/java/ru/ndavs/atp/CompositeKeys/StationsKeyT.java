package ru.ndavs.atp.CompositeKeys;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class StationsKeyT implements Serializable {
    private Long station_1T;
    private Long station_2T;
}
