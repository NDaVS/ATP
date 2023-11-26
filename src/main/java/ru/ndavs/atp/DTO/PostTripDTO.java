package ru.ndavs.atp.DTO;

import lombok.Data;
import ru.ndavs.atp.models.Driver;

@Data
public class PostTripDTO {

    private Long road_id;
    private String days;
    private String departure_time;
    private Driver driver;
}
