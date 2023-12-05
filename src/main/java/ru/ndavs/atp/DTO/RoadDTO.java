package ru.ndavs.atp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoadDTO {
    private Long id;
    private List<Integer> cost;
    private List<Integer> time;
    private List<StationDTO> stations;
}
