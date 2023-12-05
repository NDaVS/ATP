package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.CompositeKeys.GroupStationKey;
import ru.ndavs.atp.DTO.PostRStationDTO;
import ru.ndavs.atp.DTO.PostRStationsDTO;
import ru.ndavs.atp.DTO.RStationDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.Repositories.GroupRepository;
import ru.ndavs.atp.Repositories.RoadRepository;
import ru.ndavs.atp.Repositories.GroupStationRepository;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.models.Group;
import ru.ndavs.atp.models.GroupStation;
import ru.ndavs.atp.models.Station;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RStationsService {
    private final ModelMapper modelMapper;
    private final GroupStationRepository repository;
    private final GroupRepository groupRepository;
    private final StationRepository stationRepository;
    private final RoadRepository roadRepository;

    public RStationDTO getAllRStationsByGroup(Long group_id){
        Group group = groupRepository.findById(group_id).get();
        List<StationDTO> stations = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
        List<GroupStation> groupStations = repository.findAllByGroup(group);
        for (GroupStation groupStation : groupStations){
            StationDTO stationDTO = modelMapper.map(groupStation.getStation(), StationDTO.class);
            stationDTO.setSerial_number(groupStation.getSerial_number());
            stations.add(stationDTO);
            time.add(groupStation.getTime());
        }
        RStationDTO rStationDTO = new RStationDTO();
        rStationDTO.setStations(stations);
        rStationDTO.setTime(time);
        return rStationDTO;
    }

    public GroupStation addNewRStation(PostRStationDTO dto){
        GroupStation groupStation = new GroupStation();
        groupStation.setStation(stationRepository.findById(dto.getStation_id()).get());
        groupStation.setGroup(groupRepository.findById(dto.getGroup_id()).get());
        groupStation.setTime(dto.getTime());
        groupStation.setSerial_number(dto.getSerial_number());
        repository.save(groupStation);
        return groupStation;
    }
    public RStationDTO addFewRStation(PostRStationsDTO dtoRStation){
        Group group = groupRepository.findById(dtoRStation.getGroup_id()).get();
        List<StationDTO> stations  = new ArrayList<>();
        List<Integer> time = new ArrayList<>();
        List<GroupStation> groupStations = new ArrayList<>();
        for (int i = 0; i < dtoRStation.getStations_id().size(); i++){
            GroupStation groupStation = new GroupStation();
            Station station = stationRepository.findById(dtoRStation.getStations_id().get(i)).get();
            groupStation.setStation(station);
            time.add(dtoRStation.getTime().get(i));
            StationDTO stationDTO = modelMapper.map(station, StationDTO.class);
            stationDTO.setSerial_number(i);
            stations.add(stationDTO);
            groupStation.setGroup(groupRepository.findById(dtoRStation.getGroup_id()).get());
            groupStation.setTime(dtoRStation.getTime().get(i));
            groupStation.setSerial_number(i);
            repository.save(groupStation);
            groupStations.add(groupStation);
        }
        group.setStations(groupStations);
        groupRepository.save(group);
        RStationDTO rStationDTO = new RStationDTO();
        rStationDTO.setStations(stations);
        rStationDTO.setTime(time);
        return rStationDTO;
    }

    public GroupStation updateRStationByCompositeKey(PostRStationDTO postRStationDTO){
        GroupStationKey groupStationKey = new GroupStationKey();
        groupStationKey.setStation(postRStationDTO.getStation_id());
        groupStationKey.setGroup(postRStationDTO.getGroup_id());
        GroupStation groupStation = repository.getReferenceById(groupStationKey);
        groupStation.setTime(postRStationDTO.getTime());
        groupStation.setSerial_number(postRStationDTO.getSerial_number());
        groupStation.setStation(stationRepository.findById(postRStationDTO.getStation_id()).get());
        groupStation.setGroup(groupRepository.findById(postRStationDTO.getGroup_id()).get());
        repository.save(groupStation);
        return groupStation;
    }

    public GroupStation deleteRStationByCompositeKey(PostRStationDTO dto){
        GroupStationKey key = new GroupStationKey();
        key.setGroup(dto.getGroup_id());
        key.setStation(dto.getStation_id());
        GroupStation groupStation = repository.getReferenceById(key);
        repository.delete(groupStation);
        return groupStation;
    }

    public List<GroupStation> deleteRStationsByGroupId(Long id){
        Group group = groupRepository.findById(id).get();
        List<GroupStation> groupStations = group.getStations();
        repository.deleteAllByGroup(group);
        return groupStations;
    }
}
