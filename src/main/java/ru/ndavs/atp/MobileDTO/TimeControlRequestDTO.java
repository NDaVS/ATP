package ru.ndavs.atp.MobileDTO;

import lombok.Data;

import java.util.List;

@Data
public class TimeControlRequestDTO {
    private Long route_id;
    private List<String> times;
}
