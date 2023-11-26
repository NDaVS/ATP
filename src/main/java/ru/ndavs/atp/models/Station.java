package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "stations")
@Data
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    public Station(
            String name
    ) {
        this.name = name;
    }

    public Station() {

    }
}
