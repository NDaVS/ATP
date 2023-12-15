package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ndavs.atp.CompositeKeys.StationsKeyC;
import ru.ndavs.atp.CompositeKeys.StationsKeyT;

@Entity
@Table(name = "station_cost")
@Data
@NoArgsConstructor
@IdClass(StationsKeyC.class)
public class StationCost {

    @Id
    @JoinColumn(name = "station_1_id", referencedColumnName = "id")
    @ManyToOne
    private Station station_1C;

    @Id
    @JoinColumn(name = "station_2_id", referencedColumnName = "id")
    @ManyToOne
    private Station station_2C;

    @Column(name = "cost")
    private Integer cost;

}
