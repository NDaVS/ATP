package ru.ndavs.atp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

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
    @JsonIgnoreProperties("tickets")
    private Departures departures;

    private Long bus_route_id;
    private Long place_number;
    private Long trip_id;

    private Date date;
    private Time time;
    private String departure_point;
    private String place_of_arrival;

    private Boolean is_visited = false;
    private String first_name;
    private String last_name;
    private String father_name;

}
