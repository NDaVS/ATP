package ru.ndavs.atp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "sсhedule")
@Data
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_to")
    private String departure_time;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "road_id", referencedColumnName = "id")
    private Road road;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @JsonIgnoreProperties("trip")
    private Driver driver;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    @JsonIgnoreProperties("driver")
    private Bus bus;


    @ManyToMany
    @JoinTable(name = "trip_day",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "day_id", referencedColumnName = "id"))
    private List<Days> days;


}
