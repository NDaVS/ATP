package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "road")
@Data
public class Road {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "price")
    private String price;

    @Column(name = "time")
    private String time;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Station> stations;

    @Column(name = "sort")
    private String sort;

}
