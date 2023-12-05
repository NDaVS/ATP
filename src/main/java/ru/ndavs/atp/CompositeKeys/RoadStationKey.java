package ru.ndavs.atp.CompositeKeys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RoadStationKey implements Serializable {
    private Long trip;
    private Long station;
}
