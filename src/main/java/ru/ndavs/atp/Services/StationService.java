package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostStationDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.models.Station;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {
    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;
    public List<StationDTO> getStations() {
        try{
            List<Station> stations = stationRepository.findAll();
            List<StationDTO> stationDTOList = new ArrayList<>();
            for (Station station : stations) {
                stationDTOList.add(modelMapper.map(station, StationDTO.class));
            }
            return stationDTOList;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить станции: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public StationDTO getStationById(Long id) {
        try{
            Station station = stationRepository.findById(id).get();
            return modelMapper.map(station, StationDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить станцию: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public StationDTO addNewStation(PostStationDTO postStationDTO){
        try{
        Station station = modelMapper.map(postStationDTO, Station.class);
        stationRepository.save(station);
        return modelMapper.map(station, StationDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить станцию: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public StationDTO updateStationById(PostStationDTO postStationDTO, Long id){
        try {
            Station station = stationRepository.getReferenceById(id);
            station.setName(postStationDTO.getName());
            stationRepository.save(station);
            return modelMapper.map(station, StationDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить станцию: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public StationDTO deleteStationById(Long id){
        try{
            Station station = stationRepository.findById(id).get();
            stationRepository.delete(station);
            return modelMapper.map(station, StationDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить станцию: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }
}
