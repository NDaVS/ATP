package ru.ndavs.atp.DTO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@JsonSerialize
public class PostBusDTO {
    private String model;
    private String code;
    private String status;
    private Integer numberOfSits;
}

