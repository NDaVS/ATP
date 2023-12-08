//package ru.ndavs.atp.Services;
//
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import ru.ndavs.atp.CompositeKeys.RoadStationKey;
//import ru.ndavs.atp.DTO.PostRStationDTO;
//import ru.ndavs.atp.DTO.PostRStationsDTO;
//import ru.ndavs.atp.DTO.RStationDTO;
//import ru.ndavs.atp.DTO.StationDTO;
//import ru.ndavs.atp.Repositories.GroupRepository;
//import ru.ndavs.atp.Repositories.RoadRepository;
//import ru.ndavs.atp.Repositories.RoadStationRepository;
//import ru.ndavs.atp.Repositories.StationRepository;
//import ru.ndavs.atp.models.Group;
//import ru.ndavs.atp.models.RoadStation;
//import ru.ndavs.atp.models.Station;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class RStationsService {
//    private final ModelMapper modelMapper;
//    private final RoadStationRepository repository;
//    private final GroupRepository groupRepository;
//    private final StationRepository stationRepository;
//    private final RoadRepository roadRepository;
//
//    public RStationDTO getAllRStationsByGroup(Long group_id){
//        Group group = groupRepository.findById(group_id).get();
//        List<StationDTO> stations = new ArrayList<>();
//        List<Integer> time = new ArrayList<>();
//        List<RoadStation> roadStations = repository.findAllByGroup(group);
//        for (RoadStation roadStation : roadStations){
//            StationDTO stationDTO = modelMapper.map(roadStation.getStation(), StationDTO.class);
//            stationDTO.setSerial_number(roadStation.getSerial_number());
//            stations.add(stationDTO);
//            time.add(roadStation.getTime());
//        }
//        RStationDTO rStationDTO = new RStationDTO();
//        rStationDTO.setStations(stations);
//        rStationDTO.setTime(time);
//        return rStationDTO;
//    }
//
//    public RoadStation addNewRStation(PostRStationDTO dto){
//        RoadStation roadStation = new RoadStation();
//        roadStation.setStation(stationRepository.findById(dto.getStation_id()).get());
//        roadStation.setGroup(groupRepository.findById(dto.getGroup_id()).get());
//        roadStation.setTime(dto.getTime());
//        roadStation.setSerial_number(dto.getSerial_number());
//        repository.save(roadStation);
//        return roadStation;
//    }
//    public RStationDTO addFewRStation(PostRStationsDTO dtoRStation){
//        Group group = groupRepository.findById(dtoRStation.getGroup_id()).get();
//        List<StationDTO> stations  = new ArrayList<>();
//        List<Integer> time = new ArrayList<>();
//        List<RoadStation> roadStations = new ArrayList<>();
//        for (int i = 0; i < dtoRStation.getStations_id().size(); i++){
//            RoadStation roadStation = new RoadStation();
//            Station station = stationRepository.findById(dtoRStation.getStations_id().get(i)).get();
//            roadStation.setStation(station);
//            time.add(dtoRStation.getTime().get(i));
//            StationDTO stationDTO = modelMapper.map(station, StationDTO.class);
//            stationDTO.setSerial_number(i);
//            stations.add(stationDTO);
//            roadStation.setGroup(groupRepository.findById(dtoRStation.getGroup_id()).get());
//            roadStation.setTime(dtoRStation.getTime().get(i));
//            roadStation.setSerial_number(i);
//            repository.save(roadStation);
//            roadStations.add(roadStation);
//        }
//        group.setStations(roadStations);
//        groupRepository.save(group);
//        RStationDTO rStationDTO = new RStationDTO();
//        rStationDTO.setStations(stations);
//        rStationDTO.setTime(time);
//        return rStationDTO;
//    }
//
//    public RoadStation updateRStationByCompositeKey(PostRStationDTO postRStationDTO){
//        RoadStationKey roadStationKey = new RoadStationKey();
//        roadStationKey.setStation(postRStationDTO.getStation_id());
//        roadStationKey.setGroup(postRStationDTO.getGroup_id());
//        RoadStation roadStation = repository.getReferenceById(roadStationKey);
//        roadStation.setTime(postRStationDTO.getTime());
//        roadStation.setSerial_number(postRStationDTO.getSerial_number());
//        roadStation.setStation(stationRepository.findById(postRStationDTO.getStation_id()).get());
//        roadStation.setGroup(groupRepository.findById(postRStationDTO.getGroup_id()).get());
//        repository.save(roadStation);
//        return roadStation;
//    }
//
//    public RoadStation deleteRStationByCompositeKey(PostRStationDTO dto){
//        RoadStationKey key = new RoadStationKey();
//        key.setGroup(dto.getGroup_id());
//        key.setStation(dto.getStation_id());
//        RoadStation roadStation = repository.getReferenceById(key);
//        repository.delete(roadStation);
//        return roadStation;
//    }
//
//    public List<RoadStation> deleteRStationsByGroupId(Long id){
//        Group group = groupRepository.findById(id).get();
//        List<RoadStation> roadStations = group.getStations();
//        repository.deleteAllByGroup(group);
//        return roadStations;
//    }
//}
