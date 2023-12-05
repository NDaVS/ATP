package ru.ndavs.atp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PostStationsCostDTO {
    private Long stations_1_id;
    private Long stations_2_id;
    private Integer cost;
}
