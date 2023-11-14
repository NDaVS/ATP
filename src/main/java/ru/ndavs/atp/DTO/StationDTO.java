package ru.ndavs.atp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class StationDTO {
    private long id;
    private String name;

    @Override
    public String toString() {
        return "StationDTO{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
