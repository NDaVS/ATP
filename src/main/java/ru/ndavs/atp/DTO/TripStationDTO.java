package ru.ndavs.atp.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class TripStationDTO {
    private Long trip_id;
    private String time_to;
    private String time_from;
    private List<StationDTO> stations;
    public DriverDTO driverDTO;
}
