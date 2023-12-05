package ru.ndavs.atp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PostStationsCostDTO {
    private List<Long> stations_ids;
    private List<Integer> cost;
}
