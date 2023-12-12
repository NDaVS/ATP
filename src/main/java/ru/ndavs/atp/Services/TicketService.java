package ru.ndavs.atp.Services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ndavs.atp.DTO.*;
import ru.ndavs.atp.Repositories.DepartureRepository;
import ru.ndavs.atp.Repositories.TicketRepository;
import ru.ndavs.atp.models.Departures;
import ru.ndavs.atp.models.Ticket;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final DepartureRepository departureRepository;
    private final ModelMapper modelMapper;

    private final DepartureService departureService;

    public List<TicketDTO> getAllTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDTO> dtos = new ArrayList<>();
        for (Ticket t: tickets){
            dtos.add(DTO_maker(t));
        }
        return dtos;
    }
    // По рейсу
    // По дате

    public TicketDTO getTicketById(Long id){
        Ticket ticket = ticketRepository.getReferenceById(id);
        return DTO_maker(ticket);
    }

    public TicketDTO addNewTicket(PostTicketDTO postTicketDTO){
        if(postTicketDTO.getDeparture_id().equals(0L)){
            Departures departures = departureService.addNewDepartureNotDTO(new PostDepartureDTO(postTicketDTO.getDate(), postTicketDTO.getTrip_id(), "active"));
            postTicketDTO.setDeparture_id(departures.getId());
        }
        Ticket ticket = modelMapper.map(postTicketDTO, Ticket.class);
        ticket.setDepartures(departureRepository.getReferenceById(postTicketDTO.getDeparture_id()));
        Departures departures = ticket.getDepartures();
        List<Ticket> tickets = departures.getTickets();
        if(tickets == null){
            tickets = new ArrayList<>();
        }
        ticketRepository.save(ticket);
        tickets.add(ticket);
        departures.setTickets(tickets);
        departureRepository.save(departures);
        return DTO_maker(ticket);
    }

    public List<TicketDTO> addFewTickets(List<PostTicketDTO> dtos){
        List<TicketDTO> tickets = new ArrayList<>();
        boolean is_add = false;
        Long dep_id = 0L;
        for (PostTicketDTO ticketDTO: dtos){
//            Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
//            ticket.setDepartures(departureRepository.getReferenceById(ticketDTO.getDeparture_id()));
//            ticketRepository.save(ticket);
            if (is_add){

                ticketDTO.setDeparture_id(dep_id);
            }
            TicketDTO tDTO = addNewTicket(ticketDTO);
            tickets.add(tDTO);
            is_add = true;
            dep_id = tDTO.getDeparture_id();
        }

        return tickets;
    }

    public List<TicketDTO> getTicketsByDeparture(Long dep_id){
        Departures departure = departureRepository.getReferenceById(dep_id);
        List<Ticket> tickets = ticketRepository.findTicketsByDepartures(departure);
        List<TicketDTO> dtos = new ArrayList<>();
        for (Ticket t: tickets){
            dtos.add(DTO_maker(t));
        }
        return dtos;
    }

    public List<TicketDTO> getTicketsByDate(Date date){
        List<Departures> departures = departureRepository.findDeparturesByDate(date);
        List<Ticket> tickets = new ArrayList<>();
        List<TicketDTO> dtos = new ArrayList<>();
        for (Departures dep: departures){
            tickets.addAll(ticketRepository.findTicketsByDepartures(dep));
        }
        for (Ticket t: tickets){
            dtos.add(DTO_maker(t));
        }
        return dtos;
    }

    public TicketDTO updateTicketById(PostTicketDTO postTicketDTO, Long id){
        Ticket ticket = ticketRepository.getReferenceById(id);
        ticket.setDeparture_point(postTicketDTO.getDeparture_point());
        ticket.setBus_route_id(postTicketDTO.getBus_route_id());
        ticket.setFather_name(postTicketDTO.getFather_name());
        ticket.setFirst_name(postTicketDTO.getFirst_name());
        ticket.setLast_name(postTicketDTO.getLast_name());
        ticket.setIs_visited(postTicketDTO.getIs_visited());
        ticket.setPlace_number(postTicketDTO.getPlace_number());
        ticket.setPlace_of_arrival(postTicketDTO.getPlace_of_arrival());
        ticket.setDepartures(departureRepository.getReferenceById(postTicketDTO.getDeparture_id()));
        ticketRepository.save(ticket);
        return DTO_maker(ticket);
    }

    public ResponseDTO deleteTicketById(Long id){
        Ticket ticket = ticketRepository.getReferenceById(id);
        ticket.setDepartures(null);
        ticketRepository.delete(ticket);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        responseDTO.setCode(200L);
        return responseDTO;
    }

    public static TicketDTO DTO_maker(Ticket ticket){
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setDeparture_id(ticket.getDepartures().getId());
        dto.setBus_route_id(ticket.getBus_route_id());
        dto.setPlace_number(ticket.getPlace_number());
        dto.setTrip_id(ticket.getTrip_id());
        dto.setDate(ticket.getDate());
        dto.setTime(ticket.getTime());
        dto.setIs_visited(ticket.getIs_visited());
        dto.setFirst_name(ticket.getFirst_name());
        dto.setLast_name(ticket.getLast_name());
        dto.setFather_name(ticket.getFather_name());
        dto.setDeparture_point(ticket.getDeparture_point());
        dto.setPlace_of_arrival(ticket.getPlace_of_arrival());
        return dto;
    }

}
