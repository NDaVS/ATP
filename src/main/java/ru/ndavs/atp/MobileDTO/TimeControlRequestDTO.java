package ru.ndavs.atp.MobileDTO;

import lombok.Data;

import java.sql.Time;
import java.util.List;

@Data
public class TimeControlRequestDTO {
    private Long departure_id;
    private Time arrive_time;
    private String status;
}
