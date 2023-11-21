package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "trip_stations")
@Getter
@Setter
public class TripStations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_to")
    private String time_to;

    @Column(name = "time_from")
    private String time_from;

    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long trip_id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Station> stations;

    public TripStations(String timeTo, String timeFrom) {
        this.time_to = timeTo;
        this.time_from = timeFrom;
    }
    public TripStations() {
    }
}
