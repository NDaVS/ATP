package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sсhedule")
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "time_to")
    private String time_to;

    @Column(name = "time_from")
    private String time_from;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus", referencedColumnName = "id")
    private  Bus bus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_stations", referencedColumnName = "trip_id")
    private TripStations stations;



    public Schedule(
            String time_to,
            String time_from
    ) {
        this.time_to = time_to;
        this.time_from = time_from;
    }


    public Schedule() {
    }

    @Override
    public String toString() {
        return "schedule{" +
                "id=" + id +
                ", bus=" + bus +
                ", time_to='" + time_to + '\'' +
                ", time_from='" + time_from + '\'' +
                ", stations=" + stations +
                '}';
    }

}
