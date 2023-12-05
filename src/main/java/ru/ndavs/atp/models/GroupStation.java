package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ndavs.atp.CompositeKeys.GroupStationKey;

@Entity
@Table(name = "trip_station")
@Data
@NoArgsConstructor
@IdClass(GroupStationKey.class)
public class GroupStation {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "station_id", referencedColumnName = "id")
    private Station station;

    private Integer serial_number;

    private Integer time;

}
