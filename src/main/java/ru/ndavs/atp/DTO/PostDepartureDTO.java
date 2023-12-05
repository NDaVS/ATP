package ru.ndavs.atp.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PostDepartureDTO {
    private Date date;
    private Long trip_id;
    private String status;

}
