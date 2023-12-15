package ru.ndavs.atp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDepartureDTO {
    private Date date;
    private Long trip_id;
    private String status;

}
