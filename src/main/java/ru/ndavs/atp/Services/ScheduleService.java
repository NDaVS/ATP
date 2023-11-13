package ru.ndavs.atp.Services;

import org.springframework.stereotype.Service;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.models.Station;

import java.util.List;

@Service
public class ScheduleService {
    private final StationRepository stationRepository;

    public ScheduleService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getStations(){
        return stationRepository.findAll();
    }
}
