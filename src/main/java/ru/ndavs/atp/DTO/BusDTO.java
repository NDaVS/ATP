package ru.ndavs.atp.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class BusDTO {
    private Long id;
    private Long drive_id;
    private String model;
    private String code;
    private String status;
    private Integer number_of_sits;

;
}
