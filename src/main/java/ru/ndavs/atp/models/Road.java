package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "road")
@Data
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StationCost> stationCost;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StationTime> stationTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoadStation> roadStations;
}
