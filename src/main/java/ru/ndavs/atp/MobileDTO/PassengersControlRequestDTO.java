package ru.ndavs.atp.MobileDTO;

import lombok.Data;

import java.util.List;

@Data
public class PassengersControlRequestDTO {
    private Long route_id;
    private List<String> tickets_id;
}
