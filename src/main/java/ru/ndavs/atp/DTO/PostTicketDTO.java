package ru.ndavs.atp.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class PostTicketDTO {
    private Long departure_id;
    private Long bus_route_id;
    private Long place_number;
    private Long trip_id;

    private Date date;
    private String departure_point;
    private String place_of_arrival;

    private Boolean is_visited;
    private String first_name;
    private String last_name;
    private String surname;
}
