package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ndavs.atp.CompositeKeys.StationsCostKey;

@Entity
@Table(name = "station_cost")
@Data
@NoArgsConstructor
@IdClass(StationsCostKey.class)
public class StationCost {

    @Id
    @JoinColumn(name = "station_1_id", referencedColumnName = "id")
    @ManyToOne
    private Station station_1;

    @Id
    @JoinColumn(name = "station_2_id", referencedColumnName = "id")
    @ManyToOne
    private Station station_2;

    @Column(name = "cost")
    private Integer cost;

}
