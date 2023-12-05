package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.CompositeKeys.RoadStationKey;
import ru.ndavs.atp.DTO.PostRStationDTO;
import ru.ndavs.atp.Repositories.RoadStationRepository;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.Repositories.TripRepository;
import ru.ndavs.atp.models.RoadStation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RStationsService {
    private final ModelMapper modelMapper;
    private final RoadStationRepository repository;
    private final StationRepository stationRepository;
    private final TripRepository tripRepository;

    public List<RoadStation> getAllRStations(){
        return repository.findAll();
    }

    public RoadStation addNewRStation(PostRStationDTO dto){
        RoadStation roadStation = new RoadStation();
        roadStation.setStation(stationRepository.findById(dto.getStation_id()).get());
        roadStation.setTrip(tripRepository.findById(dto.getTrip_id()).get());
        roadStation.setTime(dto.getTime());
        roadStation.setSerial_number(dto.getSerial_number());
        repository.save(roadStation);
        return roadStation;
    }

    public RoadStation updateRStationByCompositeKey(PostRStationDTO postRStationDTO){
        RoadStationKey roadStationKey = new RoadStationKey();
        roadStationKey.setStation(postRStationDTO.getStation_id());
        roadStationKey.setTrip(postRStationDTO.getTrip_id());
        RoadStation roadStation = repository.getReferenceById(roadStationKey);
        roadStation.setTime(postRStationDTO.getTime());
        roadStation.setSerial_number(postRStationDTO.getSerial_number());
        roadStation.setStation(stationRepository.findById(postRStationDTO.getStation_id()).get());
        roadStation.setTrip(tripRepository.findById(postRStationDTO.getTrip_id()).get());
        repository.save(roadStation);
        return roadStation;
    }

    public RoadStation deleteRStationByCompositeKey(PostRStationDTO dto){
        RoadStationKey key = new RoadStationKey();
        key.setTrip(dto.getTrip_id());
        key.setStation(dto.getStation_id());
        RoadStation roadStation = repository.getReferenceById(key);
        roadStation.setStation(null);
        roadStation.setTrip(null);
        repository.delete(roadStation);
        return roadStation;
    }
}
