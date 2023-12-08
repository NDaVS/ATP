package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ndavs.atp.CompositeKeys.StationsKeyT;

@Entity
@Table(name = "station_cost")
@Data
@NoArgsConstructor
@IdClass(StationsKeyT.class)
public class StationTime {
    @Id
    @JoinColumn(name = "station_1_id", referencedColumnName = "id")
    @ManyToOne
    private Station station_1T;

    @Id
    @JoinColumn(name = "station_2_id", referencedColumnName = "id")
    @ManyToOne
    private Station station_2T;

    @Column(name = "time")
    private Integer time;
}
