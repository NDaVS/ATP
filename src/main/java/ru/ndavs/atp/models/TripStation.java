package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "trip_stations")
@Getter
@Setter
public class TripStation {

    @Id
    private Long trip_id;

    @OneToMany(mappedBy = "tripStation", cascade = CascadeType.ALL)
    private List<Station> stations;



    public TripStation(
            Long trip_id,
            List<Station> stations
    ) {
        this.trip_id = trip_id;
        this.stations = stations;
    }

    public TripStation() {
    }

    @Override
    public String toString() {
        return "TripStation{" +
                "trip_id=" + trip_id +
                ", stations=" + stations +
                '}';
    }

}
