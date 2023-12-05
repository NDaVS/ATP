package ru.ndavs.atp.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.*;
import ru.ndavs.atp.Repositories.*;
import ru.ndavs.atp.models.Group;
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

    private final GroupService groupService;
    private final RStationsService rStationsService;
    private final StationsCostService stationsCostService;


    public List<RoadDTO> getRoads() {
        try {
            List<Road> roads = roadRepository.findAll();
            List<RoadDTO> roadDTOList = new ArrayList<>();
            for (Road trip : roads) {
                RoadDTO roadDTO = modelMapper.map(trip, RoadDTO.class);
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
            roadDTO.setStations(rStationsService.getAllRStationsByGroup(road.getGroup().getGroup_id()).getStations());
            roadDTO.setTime(rStationsService.getAllRStationsByGroup(road.getGroup().getGroup_id()).getTime());
            roadDTO.setCost(stationsCostService.getAllCostByStation(roadDTO.getStations()));
            return roadDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить маршрут: " + e.getMessage() + " | | " + e.getStackTrace() );
        }
    }


    @Transactional
    public RoadDTO addRoad(PostRoadDTO postRoadDTO) {
        try {
            GroupDTO group = new GroupDTO();
            group.setName(postRoadDTO.getName());
            group = groupService.addGroup(group.getName());
            PostRStationsDTO postRStationsDTO = new PostRStationsDTO();
            postRStationsDTO.setGroup_id(group.getGroup_id());
            postRStationsDTO.setStations_id(postRoadDTO.getStations_id());
            postRStationsDTO.setTime(postRoadDTO.getTime());
            RStationDTO rStationDTOS =  rStationsService.addFewRStation(postRStationsDTO);
            Road road = modelMapper.map(postRoadDTO, Road.class);
            List<Station> stations = new ArrayList<>();
            for (Long index: postRoadDTO.getStations_id()){
                stations.add(stationRepository.findById(index).get());
            }
//            road.setStations(stations);
            roadRepository.save(road);
            RoadDTO roadDTO = modelMapper.map(road, RoadDTO.class);
            roadDTO.setCost(stationsCostService.getAllCostByStation(rStationDTOS.getStations()));
            roadDTO.setTime(rStationDTOS.getTime());
            roadDTO.setStations(rStationDTOS.getStations());
            return roadDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }
    @Transactional
    public ResponseDTO deleteTrip(Long id) {
        try {
            Road road = roadRepository.findById(id).get();
//            road.setStations(null);
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

            List<Station> stations = new ArrayList<>();
            for (Long station : postRoadDTO.getStations_id()) {
                stations.add(stationRepository.findById(station).get());
            }

            roadRepository.save(road);
            RoadDTO roadDTO = modelMapper.map(road, RoadDTO.class);

            return roadDTO;

        } catch (Exception e) {
            throw new IllegalStateException("Не удалось обновить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }




}
