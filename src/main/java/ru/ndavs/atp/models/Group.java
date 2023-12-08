package ru.ndavs.atp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "group_station")
@Data

public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long group_id;


    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("group")
    @JoinColumn(name = "group_station_id", referencedColumnName = "group_id")
    private List<RoadStation> stations;

}
