package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_id", referencedColumnName = "id")
    private Departures departures;

    private Long bus_route_id;
    private Long place_number;

    private String departure_point;
    private String place_of_arrival;

    private Boolean is_visited;
    private String first_name;
    private String last_name;
    private String surname;

}
