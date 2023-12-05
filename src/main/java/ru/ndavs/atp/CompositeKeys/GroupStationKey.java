package ru.ndavs.atp.CompositeKeys;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class GroupStationKey implements Serializable {
    private Long group;
    private Long station;
}
