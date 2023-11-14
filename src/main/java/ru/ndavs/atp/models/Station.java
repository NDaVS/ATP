package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "stations")
@Getter
@Setter
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

    @Override
    public String toString() {
        return "station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
