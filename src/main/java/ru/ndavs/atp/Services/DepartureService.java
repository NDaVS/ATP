package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.MemberSubstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.DepartureDTO;
import ru.ndavs.atp.DTO.PostDepartureDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.DTO.TicketDTO;
import ru.ndavs.atp.Repositories.DepartureRepository;
import ru.ndavs.atp.Repositories.TripRepository;
import ru.ndavs.atp.models.Departures;
import ru.ndavs.atp.models.Ticket;
import ru.ndavs.atp.models.Trip;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private final TripService tripService;

    public List<DepartureDTO> getAllDepartures(){
        List<Departures> departures = departureRepository.findAll();
        List<DepartureDTO> departureDTOList = new ArrayList<>();
        for (Departures deps: departures) {
            DepartureDTO departureDTO = DTO_maker(deps);
            departureDTOList.add(departureDTO);
        }
        return departureDTOList;
    }

    public DepartureDTO getDepartureById(Long id){
        Departures departure = departureRepository.findById(id).get();
        DepartureDTO departureDTO = DTO_maker(departure);
        return departureDTO;
    }

    public List<DepartureDTO> getDoneDepartures(){
        List<Departures> deps = departureRepository.findDeparturesByStatus("done");
        List<DepartureDTO> dtos = new ArrayList<>();
        for(Departures d: deps){
            dtos.add(DTO_maker(d));
        }
        return dtos;
    }

    public List<DepartureDTO> getCanceledDepartures(){
        List<Departures> deps = departureRepository.findDeparturesByStatus("canceled");
        List<DepartureDTO> dtos = new ArrayList<>();
        for(Departures d: deps){
            dtos.add(DTO_maker(d));
        }
        return dtos;
    }


    public DepartureDTO addNewDeparture(PostDepartureDTO postDepartureDTO){
        Departures departure = new Departures();
        departure.setDate(postDepartureDTO.getDate());
        departure.setStatus(postDepartureDTO.getStatus());
        Trip trip = tripRepository.findById(postDepartureDTO.getTrip_id()).get();
        departure.setTrip(trip);
        departureRepository.save(departure);
        return DTO_maker(departure);
    }

    public Departures addNewDepartureNotDTO(PostDepartureDTO postDepartureDTO){
        Departures departure = new Departures();
        departure.setDate(postDepartureDTO.getDate());
        departure.setStatus(postDepartureDTO.getStatus());
        Trip trip = tripRepository.findById(postDepartureDTO.getTrip_id()).get();
        departure.setTrip(trip);
        departureRepository.save(departure);
        return departure;
    }

    public DepartureDTO updateDepartureById(PostDepartureDTO postDepartureDTO, Long id){
        Departures departure = departureRepository.findById(id).get();
        departure.setTrip(tripRepository.findById(postDepartureDTO.getTrip_id()).get());
        departure.setDate(postDepartureDTO.getDate());
        departure.setStatus(postDepartureDTO.getStatus());
        departureRepository.save(departure);
        return DTO_maker(departure);
    }

    public ResponseDTO deleteDepartureById(Long id){

        departureRepository.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(200L);
        responseDTO.setMessage("Success");
        return responseDTO;
    }

    public DepartureDTO DTO_maker(Departures departure){
        DepartureDTO dto = new DepartureDTO();
        List<TicketDTO> tickets = new ArrayList<>();
        if (!(departure.getTickets() == null)){
            for (Ticket t: departure.getTickets()){
                tickets.add(TicketService.DTO_maker(t));
            }
            dto.setTickets(tickets);

        }
        dto.setId(departure.getId());
        dto.setStatus(departure.getStatus());
        dto.setDate(departure.getDate());
        dto.setTrip(tripService.getTripById(departure.getTrip().getId()));
        return dto;
    }
}
