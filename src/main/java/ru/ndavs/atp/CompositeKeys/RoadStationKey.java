package ru.ndavs.atp.CompositeKeys;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class RoadStationKey implements Serializable {
    private Long trip;
    private Long station;
}
