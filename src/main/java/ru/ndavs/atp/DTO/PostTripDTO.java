package ru.ndavs.atp.DTO;

import lombok.Data;
import ru.ndavs.atp.models.Driver;

import java.sql.Time;
import java.util.List;
import java.util.Timer;

@Data
public class PostTripDTO {

    private Long road_id;
    private List<Long> days_id  ;
    private String departure_time;
    private Long driver_id;
    private Long bus_id;
}
