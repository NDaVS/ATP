package ru.ndavs.atp.MobileDTO;

import lombok.Data;

import java.util.List;

@Data
public class TimeControlRequestDTO {
    private Long departure_id;
    private String status;
}
