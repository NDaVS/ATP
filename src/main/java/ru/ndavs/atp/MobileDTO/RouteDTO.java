package ru.ndavs.atp.MobileDTO;

import lombok.Data;

import java.util.List;

@Data
public class RouteDTO {
    private Long id;
    private String time_start;
    private String time_finish;
    private List<String> stations;
    private Integer place_number;
}
