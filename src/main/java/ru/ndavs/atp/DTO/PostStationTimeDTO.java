package ru.ndavs.atp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PostStationTimeDTO {
    private Long stations_1_id;
    private Long stations_2_id;
    private Integer time;
}
