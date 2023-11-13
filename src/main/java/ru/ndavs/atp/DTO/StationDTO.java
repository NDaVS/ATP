package ru.ndavs.atp.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class StationDTO {
    private Integer id;
    private Integer name;

    @Override
    public String toString() {
        return "StationDTO{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
