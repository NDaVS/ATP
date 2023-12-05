package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ndavs.atp.CompositeKeys.RoadStationKey;

@Entity
@Table(name = "trip_station")
@Data
@NoArgsConstructor
@IdClass(RoadStationKey.class)
public class RoadStation {
    @Id
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;

    @Id
    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "id")
    private Station station;

    private Integer serial_number;

    private Integer time;

}
