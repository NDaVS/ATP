package ru.ndavs.atp.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class TripDTO {
    private Long id;
    private String departure_time;
    private List<Long> days;
    private DriverDTO driver;
    private BusDTO bus;
    private RoadDTO road;
}
