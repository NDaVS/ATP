package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "road", referencedColumnName = "id")
    private Road road;

    @Column(name = "days")
    private String days;

    @OneToOne(cascade = CascadeType.ALL)
    private Driver driver;

    public Trip(
            String time_to
    ) {
        this.departure_time = time_to;
    }
}
