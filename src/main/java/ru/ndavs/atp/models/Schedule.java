package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sсhedule")
@Getter
@Setter
public class Schedule {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus", referencedColumnName = "id")
    private  Bus bus;

    @Column(name = "time_to")
    private String time_to;

    @Column(name = "time_from")
    private String time_from;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trip_stations", referencedColumnName = "trip_id")
    private TripStation stations;

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
