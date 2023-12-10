package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.MobileDTO.PassengersControlRequestDTO;
import ru.ndavs.atp.MobileDTO.TimeControlRequestDTO;
import ru.ndavs.atp.Repositories.DepartureRepository;
import ru.ndavs.atp.Repositories.DriverRepository;
import ru.ndavs.atp.Repositories.RoadRepository;
import ru.ndavs.atp.Repositories.TicketRepository;
import ru.ndavs.atp.models.Departures;
import ru.ndavs.atp.models.RoadStation;
import ru.ndavs.atp.models.Road;
import ru.ndavs.atp.models.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class MobileService {
    private final ModelMapper modelMapper;
    private final DriverRepository driverRepository;
    private final TicketRepository ticketRepository;
    private final DepartureRepository departureRepository;

    public void markPassengers(PassengersControlRequestDTO passengers) {
        for (Long id: passengers.getTickets_id()){
            Optional<Ticket> ticket = ticketRepository.findById(id);
            if (ticket.isEmpty()) throw new IllegalStateException("The ticket was not found");
            Ticket t = ticket.get();
            t.setIs_visited(true);
            ticketRepository.save(t);
        }
//        passengers.getTickets_id().stream()
//                .map(string_id -> {
//                    Long id = Long.parseLong(string_id);
//                    Optional<Ticket> ticket = ticketRepository.findById(id);
//                    if (ticket.isEmpty()) throw new IllegalStateException("The ticket was not found");
//                    Ticket t = ticket.get();
//                    t.setIs_visited(true);
//                    ticketRepository.save(t);
//                    return id;
//                });
    }

    public ResponseDTO markTime(TimeControlRequestDTO time) {
        Optional<Departures> dep = departureRepository.findById(time.getDeparture_id());
        if (dep.isEmpty()) throw new IllegalStateException("The dep does not exist");
        Departures departure = dep.get();
        departure.setStatus(time.getStatus());
        departureRepository.save(departure);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(200L);
        responseDTO.setMessage("Success");
        return responseDTO;
    }
}
