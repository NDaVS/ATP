package ru.ndavs.atp.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class TripStationDTO {
    private Long trip_id;
    private List<StationDTO> stations;
}
