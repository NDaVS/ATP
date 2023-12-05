package ru.ndavs.atp.DTO;

import lombok.Data;

@Data
public class DriverDTO extends UserResponseDTO {
    public String driver_id;
    public Long bus_id;
}
