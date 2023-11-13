package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "trip_stations")
@Getter
@Setter
public class TripStation {

    @Id
    private Long trip_id;

//    @OneToMany(mappedBy = "trip_station")
//    @JoinColumn(name = "station", referencedColumnName = "id")
//    private Set<Station> station_id;

    public TripStation(
            Long trip_id,
            Set<Station> station_id
    ) {
        this.trip_id = trip_id;
//        this.station_id = station_id;
    }
    public TripStation() {
    }

    @Override
    public String toString() {
        return "trip_station{" +
                "trip_id=" + trip_id +
//                ", station_id=" + station_id +
                '}';
    }


}
