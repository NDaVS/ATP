package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stations")
public class Station {

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
