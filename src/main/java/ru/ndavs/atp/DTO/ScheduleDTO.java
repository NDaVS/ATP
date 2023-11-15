package ru.ndavs.atp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class ScheduleDTO {
    private Long id;
    private String time_to;
    private String time_from;
    private BusDTO bus;
    private TripStationDTO station;
}
