package ru.ndavs.atp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RoadDTO {
    private Long id;
    private List<String> price;
    private List<String> time;
    private List<StationDTO> stations;
    public String sort;
}
