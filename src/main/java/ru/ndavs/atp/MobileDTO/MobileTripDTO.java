package ru.ndavs.atp.MobileDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ndavs.atp.DTO.BusDTO;
import ru.ndavs.atp.DTO.DriverDTO;
import ru.ndavs.atp.DTO.RoadDTO;

import java.util.List;

@Data
@NoArgsConstructor
public class MobileTripDTO {
    private Long id;
    private String departure_time;
    private List<String> days;
    private DriverDTO driver;
    private BusDTO bus;
    private List<String> stations;
}
