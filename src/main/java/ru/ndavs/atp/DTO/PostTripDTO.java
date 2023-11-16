package ru.ndavs.atp.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class PostTripDTO {
    private Long trip_id;
    private Long bus_id;
    private String time_to;
    private String time_from;
    private List<StationDTO> stations;

    @Override
    public String toString() {
        return "PostScheduleDTO{" +
                "bus_id=" + bus_id +
                ", time_to='" + time_to + '\'' +
                ", time_from='" + time_from + '\'' +
                ", stations=" + stations +
                '}';
    }
}
