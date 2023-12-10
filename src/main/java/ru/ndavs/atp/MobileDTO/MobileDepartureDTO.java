package ru.ndavs.atp.MobileDTO;

import lombok.Data;
import ru.ndavs.atp.DTO.TicketDTO;
import ru.ndavs.atp.DTO.TripDTO;

import java.util.Date;
import java.util.List;

@Data
public class MobileDepartureDTO {
    private Long id;
    private Date date;
    private String status;
    private List<TicketDTO> tickets;
    private MobileTripDTO trip;
}
