package ru.ndavs.atp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class RStationDTO {
    List<StationDTO> stations;
    List<Integer> time;
}
