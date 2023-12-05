package ru.ndavs.atp.DTO;

import lombok.Data;

@Data
public class PostRStationDTO {
    private Long trip_id;
    private Long station_id;
    private Integer serial_number;
    private Integer time;
}
