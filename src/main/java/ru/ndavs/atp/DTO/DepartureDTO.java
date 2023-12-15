package ru.ndavs.atp.DTO;

import lombok.Data;
import ru.ndavs.atp.models.Ticket;

import java.util.Date;
import java.util.List;

@Data
public class DepartureDTO {
    private Long id;
    private Date date;
    private String status;
    private List<TicketDTO> tickets;
    private TripDTO trip;
}
