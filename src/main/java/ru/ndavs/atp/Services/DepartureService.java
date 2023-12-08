package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.DepartureDTO;
import ru.ndavs.atp.DTO.PostDepartureDTO;
import ru.ndavs.atp.Repositories.DepartureRepository;
import ru.ndavs.atp.Repositories.TripRepository;
import ru.ndavs.atp.models.Departures;

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
            DepartureDTO departureDTO = modelMapper.map(deps, DepartureDTO.class);
            departureDTO.setTrip(tripService.getTripById(deps.getTrip().getId()));
            departureDTOList.add(departureDTO);
        }
        return departureDTOList;
    }

    public Departures getDepartureById(Long id){
        Departures departure = departureRepository.findById(id).get();
        DepartureDTO departureDTO = modelMapper.map(departure, DepartureDTO.class);
        departureDTO.setTrip(tripService.getTripById(departure.getTrip().getId()));
        return departure;
    }

    public Departures addNewDeparture(PostDepartureDTO postDepartureDTO){
        Departures departure = modelMapper.map(postDepartureDTO, Departures.class);
        departure.setTrip(tripRepository.findById(postDepartureDTO.getTrip_id()).get());
        departureRepository.save(departure);
        return departure;
    }

    public Departures updateDepartureById(PostDepartureDTO postDepartureDTO, Long id){
        Departures departure = departureRepository.findById(id).get();
        departure.setTrip(tripRepository.findById(postDepartureDTO.getTrip_id()).get());
        departure.setDate(postDepartureDTO.getDate());
        departureRepository.save(departure);
        return departure;
    }

    public Departures deleteDepartureById(Long id){
        Departures departure = departureRepository.findById(id).get();
        departure.setTrip(null);
        departureRepository.delete(departure);
        return departure;
    }
}
