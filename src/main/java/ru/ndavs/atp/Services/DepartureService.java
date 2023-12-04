package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostDepartureDTO;
import ru.ndavs.atp.Repositories.DepartureRepository;
import ru.ndavs.atp.models.Departures;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartureService {
    private final DepartureRepository departureRepository;
    private final ModelMapper modelMapper;

    public List<Departures> getAllDepartures(){
        List<Departures> departures = departureRepository.findAll();
        return departures;
    }

    public Departures getDepartureById(Long id){
        Departures departure = departureRepository.findById(id).get();
        return departure;
    }

    public Departures addNewDeparture(PostDepartureDTO postDepartureDTO){
        Departures departure = modelMapper.map(postDepartureDTO, Departures.class);
        departureRepository.save(departure);
        return departure;
    }

    public Departures updateDepartureById(PostDepartureDTO postDepartureDTO, Long id){
        Departures departure = departureRepository.findById(id).get();
        departure.setDate(postDepartureDTO.getDate());
        departureRepository.save(departure);
        return departure;
    }

    public Departures deleteDepartureById(Long id){
        Departures departure = departureRepository.findById(id).get();
        departureRepository.delete(departure);
        return departure;
    }
}
