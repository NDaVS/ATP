package ru.ndavs.atp.CompositeKeys;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class StationsCostKey implements Serializable {
    private Long station_1;
    private Long station_2;
}
