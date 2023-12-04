package ru.ndavs.atp.Services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ndavs.atp.DTO.PostBusSpecDTO;
import ru.ndavs.atp.DTO.PostTicketDTO;
import ru.ndavs.atp.Repositories.DepartureRepository;
import ru.ndavs.atp.Repositories.TicketRepository;
import ru.ndavs.atp.models.Ticket;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final DepartureRepository departureRepository;
    private final ModelMapper modelMapper;

    public List<Ticket> getAllTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets;
    }
    // По рейсу
    // По дате

    public Ticket getTicketById(Long id){
        Ticket ticket = ticketRepository.getReferenceById(id);
        return ticket;
    }

    public Ticket addNewTicket(PostTicketDTO postTicketDTO){
        Ticket ticket = modelMapper.map(postTicketDTO, Ticket.class);
        ticket.setDepartures(departureRepository.getReferenceById(postTicketDTO.getDeparture_id()));
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket updateTicketById(PostTicketDTO postTicketDTO, Long id){
        Ticket ticket = ticketRepository.getReferenceById(id);
        ticket.setDeparture_point(postTicketDTO.getDeparture_point());
        ticket.setBus_route_id(postTicketDTO.getBus_route_id());
        ticket.setSurname(postTicketDTO.getSurname());
        ticket.setFirst_name(postTicketDTO.getFirst_name());
        ticket.setLast_name(postTicketDTO.getLast_name());
        ticket.setIs_visited(postTicketDTO.getIs_visited());
        ticket.setPlace_number(postTicketDTO.getPlace_number());
        ticket.setPlace_of_arrival(postTicketDTO.getPlace_of_arrival());
        ticket.setDepartures(departureRepository.getReferenceById(postTicketDTO.getDeparture_id()));
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket deleteTicketById(Long id){
        Ticket ticket = ticketRepository.getReferenceById(id);
        ticket.setDepartures(null);
        ticketRepository.delete(ticket);
        return ticket;
    }

}
