package ru.ndavs.atp.DTO;

import lombok.Data;

@Data
public class TicketDTO {
    private Long departure_id;
    private Long trip_id;
    private Long place_number;
    private String place_from;
    private String place_to;
}
