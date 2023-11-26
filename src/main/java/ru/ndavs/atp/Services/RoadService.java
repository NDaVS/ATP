package ru.ndavs.atp.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostRoadDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.DTO.RoadDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.DriverRepository;
import ru.ndavs.atp.Repositories.RoadRepository;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.models.Road;
import ru.ndavs.atp.models.Station;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoadService {
    private final RoadRepository roadRepository;
    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;


    public List<RoadDTO> getRoads() {
        try {
            List<Road> roads = roadRepository.findAll();
            List<RoadDTO> roadDTOList = new ArrayList<>();
            for (Road trip : roads) {
                RoadDTO roadDTO = modelMapper.map(trip, RoadDTO.class);
                roadDTO.setTime(List.of(trip.getTime().split(" ")));
                roadDTO.setPrice(List.of(trip.getPrice().split(" ")));
                roadDTOList.add(roadDTO);
            }
            return roadDTOList;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить маршруты: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public RoadDTO getRoadById(Long id){
        try {
            Road road = roadRepository.findById(id).get();
            RoadDTO roadDTO = modelMapper.map(road, RoadDTO.class);
            roadDTO.setTime(List.of(road.getTime().split(" ")));
            roadDTO.setPrice(List.of(road.getPrice().split(" ")));
            return roadDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить маршрут: " + e.getMessage() + " | | " + e.getStackTrace() );
        }
    }


    @Transactional
    public RoadDTO addRoad(PostRoadDTO postRoadDTO) {
        try {
            Road road = modelMapper.map(postRoadDTO, Road.class);
            List<Station> stations = new ArrayList<>();
            for (Long index: postRoadDTO.getStations_id()){
                stations.add(stationRepository.findById(index).get());
            }
            road.setStations(stations);
            roadRepository.save(road);
            RoadDTO roadDTO = modelMapper.map(road, RoadDTO.class);
            roadDTO.setTime(List.of(road.getTime().split(" ")));
            roadDTO.setPrice(List.of(road.getPrice().split(" ")));
            return roadDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }
    @Transactional
    public ResponseDTO deleteTrip(Long id) {
        try {
            Road road = roadRepository.findById(id).get();
            road.setStations(null);
            roadRepository.delete(road);
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "success";
            responseDTO.code = 200L;
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось удалить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    @Transactional
    public RoadDTO updateTrip(Long id, PostRoadDTO postRoadDTO) {
        try {
            Road road = roadRepository.findById(id).get();
            road.setTime(postRoadDTO.getTime());
            road.setPrice(postRoadDTO.getPrice());
            List<Station> stations = new ArrayList<>();
            for (Long station : postRoadDTO.getStations_id()) {
                stations.add(stationRepository.findById(station).get());
            }
            road.setStations(stations);
            road.setSort(postRoadDTO.getSort());
            roadRepository.save(road);
            RoadDTO roadDTO = modelMapper.map(road, RoadDTO.class);
            roadDTO.setTime(List.of(road.getTime().split(" ")));
            roadDTO.setPrice(List.of(road.getPrice().split(" ")));
            return roadDTO;

        } catch (Exception e) {
            throw new IllegalStateException("Не удалось обновить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }



    public ResponseDTO getStations() {
        try{
            List<Station> stations = stationRepository.findAll();
            List<StationDTO> stationDTOList = new ArrayList<>();
            for (Station station : stations) {
                stationDTOList.add(modelMapper.map(station, StationDTO.class));
            }
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.message = "success";
            responseDTO.code = 200L;
            responseDTO.data = stationDTOList;
            return responseDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить станции: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }
}
