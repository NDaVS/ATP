package ru.ndavs.atp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PostRStationsDTO {
    private Long group_id;
    private List<Long> stations_id;
    private List<Integer> time;
}
