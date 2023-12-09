package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.MemberSubstitution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.DepartureDTO;
import ru.ndavs.atp.DTO.PostDepartureDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.Repositories.DepartureRepository;
import ru.ndavs.atp.Repositories.TripRepository;
import ru.ndavs.atp.models.Departures;
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
            DepartureDTO departureDTO = new DepartureDTO();
            departureDTO.setId(deps.getId());
            departureDTO.setDate(deps.getDate());
            departureDTO.setStatus(deps.getStatus());
            departureDTO.setTrip(tripService.getTripById(deps.getTrip().getId()));
            departureDTOList.add(departureDTO);
        }
        return departureDTOList;
    }

    public Departures getDepartureById(Long id){
        Departures departure = departureRepository.findById(id).get();
        DepartureDTO departureDTO = new DepartureDTO();
        departureDTO.setId(departure.getId());
        departureDTO.setDate(departure.getDate());
        departureDTO.setStatus(departure.getStatus());
        departureDTO.setTrip(tripService.getTripById(departure.getTrip().getId()));
        return departure;
    }

    public Departures addNewDeparture(PostDepartureDTO postDepartureDTO){
        Departures departure = new Departures();
        departure.setDate(postDepartureDTO.getDate());
        departure.setStatus(postDepartureDTO.getStatus());
        Trip trip = tripRepository.findById(postDepartureDTO.getTrip_id()).get();
        departure.setTrip(trip);
        departureRepository.save(departure);
        return departure;
    }

    public Departures updateDepartureById(PostDepartureDTO postDepartureDTO, Long id){
        Departures departure = departureRepository.findById(id).get();
        departure.setTrip(tripRepository.findById(postDepartureDTO.getTrip_id()).get());
        departure.setDate(postDepartureDTO.getDate());
        departure.setStatus(postDepartureDTO.getStatus());
        departureRepository.save(departure);
        return departure;
    }

    public ResponseDTO deleteDepartureById(Long id){

        departureRepository.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(200L);
        responseDTO.setMessage("Success");
        return responseDTO;
    }
}
