package ru.ndavs.atp.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StationDTO {
    private Long id;
    private String name;
    private Integer serial_number;

}
