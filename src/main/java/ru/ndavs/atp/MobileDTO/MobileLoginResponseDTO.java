package ru.ndavs.atp.MobileDTO;

import lombok.Data;

import java.util.List;

@Data
public class MobileLoginResponseDTO {
    private String first_name;
    private String last_name;
    private String patronymic;
    private Long bus_code;
    private String token;
    private List<RouteDTO> daily_schedule;
}
