package ru.ndavs.atp.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.CompositeKeys.RoadStationKey;
import ru.ndavs.atp.CompositeKeys.StationsKeyC;
import ru.ndavs.atp.CompositeKeys.StationsKeyT;
import ru.ndavs.atp.DTO.*;
import ru.ndavs.atp.Repositories.*;
import ru.ndavs.atp.models.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class RoadService {
    private final RoadRepository roadRepository;
    private final StationRepository stationRepository;
    private final RoadStationRepository roadStationRepository;
    private final StationCostRepository stationCostRepository;
    private final StationTimeRepository stationTimeRepository;
    private final ModelMapper modelMapper;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;

    //    private final RStationsService rStationsService;
    private final StationsCostService stationsCostService;
    private final StationTimeService stationTimeService;



    public List<RoadDTO> getRoads() {
        try {
            List<Road> roads = roadRepository.findAll();
            List<RoadDTO> roadDTOList = new ArrayList<>();
            for (Road road : roads) {
                roadDTOList.add(getRoadInfo(road));
            }
            return roadDTOList;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить маршруты: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public RoadDTO getRoadById(Long id) {
        try {
            Road road = roadRepository.findById(id).get();
            return getRoadInfo(road);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    private RoadDTO getRoadInfo(Road road) {
        RoadDTO roadDTO = new RoadDTO();
        roadDTO.setId(road.getId());
        List<RoadStation> roadStations = road.getRoadStations();
        List<Station> stations = new ArrayList<>();
        List<Integer> cost = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
        roadStations.sort(Comparator.comparing(RoadStation::getSerial_number));
        for (RoadStation roadStation : roadStations) {
            stations.add(roadStation.getStation());
        }
        roadDTO.setStations(stations.stream().map(station -> modelMapper.map(station, StationDTO.class)).toList());
        for (int i = 0; i < stations.size() - 1; i++) {
            StationsKeyT stationsKeyT = new StationsKeyT();
            stationsKeyT.setStation_1T(stations.get(i).getId());
            stationsKeyT.setStation_2T(stations.get(i + 1).getId());
            StationsKeyC stationsKeyC = new StationsKeyC();
            stationsKeyC.setStation_1C(stations.get(i).getId());
            stationsKeyC.setStation_2C(stations.get(i + 1).getId());
            cost.add(stationCostRepository.findById(stationsKeyC).get().getCost());
            time.add(stationTimeRepository.findById(stationsKeyT).get().getTime());
        }
        roadDTO.setCost(cost);
        roadDTO.setTime(time);
        return roadDTO;
    }



    public RoadDTO addRoad(PostRoadDTO postRoadDTO) {
        try {
            Road road = new Road();

            List<RoadStation> roadStationsForId = roadStationRepository.findAll();
            roadStationsForId.sort(Comparator.comparing(RoadStation::getId));
            Long id;
            if (roadStationsForId.isEmpty()) {
                id = 1L;
            } else {
                id = roadStationsForId.get(roadStationsForId.size() - 1).getId() + 1;
            }

            List<RoadStation> roadStations = new ArrayList<>();
            for (int i = 0; i < postRoadDTO.getStations_id().size(); i++) {
                RoadStation roadStation = new RoadStation();

                roadStation.setStation(stationRepository.findById(postRoadDTO.getStations_id().get(i)).get());
                roadStation.setSerial_number(i);
                roadStation.setId(id);

                roadStations.add(roadStation);
            }
            road.setRoadStations(roadStations);


            List<Integer> cost = new ArrayList<>();
            List<Integer> time = new ArrayList<>();
            List<StationTime> stationTimes = new ArrayList<>();
            List<StationCost> stationCosts = new ArrayList<>();
            for (int i = 0; i < postRoadDTO.getStations_id().size() - 1; i++) {
                StationsKeyT stationsKeyT = new StationsKeyT();
                stationsKeyT.setStation_1T(postRoadDTO.getStations_id().get(i));
                stationsKeyT.setStation_2T(postRoadDTO.getStations_id().get(i + 1));

                StationsKeyC stationsKeyC = new StationsKeyC();
                stationsKeyC.setStation_1C(postRoadDTO.getStations_id().get(i));
                stationsKeyC.setStation_2C(postRoadDTO.getStations_id().get(i + 1));

                StationCost stationCost = stationCostRepository.findById(stationsKeyC).get();
                stationCosts.add(stationCost);
                cost.add(stationCost.getCost());

                StationTime stationTime = stationTimeRepository.findById(stationsKeyT).get();
                stationTimes.add(stationTime);
                time.add(stationTime.getTime());
            }
            road.setStationCost(stationCosts);
            road.setStationTime(stationTimes);
            roadRepository.save(road);
            RoadDTO roadDTO = new RoadDTO();
            roadDTO.setId(road.getId());
            roadDTO.setCost(cost);
            roadDTO.setTime(time);
            List<StationDTO> stationDTOList = new ArrayList<>();
            List<RoadStation> stations = road.getRoadStations();
            stations.sort(Comparator.comparing(RoadStation::getSerial_number));
            for (RoadStation station : stations) {
                StationDTO stationDTO = new StationDTO();
                stationDTO.setName(station.getStation().getName());
                stationDTO.setId(station.getStation().getId());
                stationDTOList.add(stationDTO);
            }
            roadDTO.setStations(stationDTOList);
            return roadDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }


    public ResponseDTO deleteTrip(Long id) {
        try {
            Road road = roadRepository.findById(id).get();
            for (RoadStation roadStation : road.getRoadStations()) {
                RoadStationKey roadStationKey = new RoadStationKey();
                roadStationKey.setId(roadStation.getId());
                roadStationKey.setStation(roadStation.getStation().getId());

                RoadStation roadStation_del = roadStationRepository.findById(roadStationKey).get();
                roadStationRepository.delete(roadStation_del);
            }
            road.setStationCost(null);
            road.setStationTime(null);
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
//            Road road = roadRepository.findById(id).get();
//            roadStationRepository.deleteAllById(id);
            Road road = roadRepository.findById(id).get();
            for (RoadStation roadStation : road.getRoadStations()) {
                RoadStationKey roadStationKey = new RoadStationKey();
                roadStationKey.setId(roadStation.getId());
                roadStationKey.setStation(roadStation.getStation().getId());

                RoadStation roadStation_del = roadStationRepository.findById(roadStationKey).get();
                roadStationRepository.delete(roadStation_del);
            }
            List<RoadStation> roadStationsForId = roadStationRepository.findAll();
            roadStationsForId.sort(Comparator.comparing(RoadStation::getId));
            Long RoadStation_id;
            if (roadStationsForId.isEmpty()) {
                RoadStation_id = 1L;
            } else {
                RoadStation_id = roadStationsForId.get(roadStationsForId.size() - 1).getId() + 1;
            }

            List<RoadStation> roadStations = new ArrayList<>();
            for (int i = 0; i < postRoadDTO.getStations_id().size(); i++) {
                RoadStation roadStation = new RoadStation();

                roadStation.setStation(stationRepository.findById(postRoadDTO.getStations_id().get(i)).get());
                roadStation.setSerial_number(i);
                roadStation.setId(RoadStation_id);

                roadStations.add(roadStation);
            }
            road.setRoadStations(roadStations);


            List<Integer> cost = new ArrayList<>();
            List<Integer> time = new ArrayList<>();
            List<StationTime> stationTimes = new ArrayList<>();
            List<StationCost> stationCosts = new ArrayList<>();
            for (int i = 0; i < postRoadDTO.getStations_id().size() - 1; i++) {
                StationsKeyT stationsKeyT = new StationsKeyT();
                stationsKeyT.setStation_1T(postRoadDTO.getStations_id().get(i));
                stationsKeyT.setStation_2T(postRoadDTO.getStations_id().get(i + 1));

                StationsKeyC stationsKeyC = new StationsKeyC();
                stationsKeyC.setStation_1C(postRoadDTO.getStations_id().get(i));
                stationsKeyC.setStation_2C(postRoadDTO.getStations_id().get(i + 1));

                StationCost stationCost = stationCostRepository.findById(stationsKeyC).get();
                stationCosts.add(stationCost);
                cost.add(stationCost.getCost());

                StationTime stationTime = stationTimeRepository.findById(stationsKeyT).get();
                stationTimes.add(stationTime);
                time.add(stationTime.getTime());
            }
            road.setStationCost(stationCosts);
            road.setStationTime(stationTimes);
            roadRepository.save(road);
            RoadDTO roadDTO = new RoadDTO();
            roadDTO.setId(road.getId());
            roadDTO.setCost(cost);
            roadDTO.setTime(time);
            List<StationDTO> stationDTOList = new ArrayList<>();
            List<RoadStation> stations = road.getRoadStations();
            stations.sort(Comparator.comparing(RoadStation::getSerial_number));
            for (RoadStation station : stations) {
                StationDTO stationDTO = new StationDTO();
                stationDTO.setName(station.getStation().getName());
                stationDTO.setId(station.getStation().getId());
                stationDTOList.add(stationDTO);
            }
            roadDTO.setStations(stationDTOList);
            return roadDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось обновить маршрут: " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }


}
