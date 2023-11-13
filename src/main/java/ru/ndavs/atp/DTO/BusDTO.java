package ru.ndavs.atp.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class BusDTO {
    private Long id;
    private String model;
    private String Status;
}
