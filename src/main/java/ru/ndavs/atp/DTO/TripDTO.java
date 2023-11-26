package ru.ndavs.atp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class TripDTO {
    private Long id;
    private String departure_time;
    private String days;
    private DriverDTO driver;
    private RoadDTO road;
}
