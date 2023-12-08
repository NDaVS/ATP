package ru.ndavs.atp.Services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.CompositeKeys.StationsKeyT;
import ru.ndavs.atp.DTO.PostStationTimeDTO;
import ru.ndavs.atp.DTO.StationTimeDTO;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.Repositories.StationTimeRepository;
import ru.ndavs.atp.models.StationTime;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationTimeService {
    private final StationTimeRepository stationTimeRepository;
    private final StationRepository stationRepository;
    private final ModelMapper modelMapper;


    public List<StationTimeDTO> getAllTime() {
        List<StationTime> stationTimes = stationTimeRepository.findAll();
        List<StationTimeDTO> stationTimeDTOList = new ArrayList<>();
        for (StationTime stationTime : stationTimes) {
            StationTimeDTO stationTimeDTO = new StationTimeDTO();
            stationTimeDTO.setStation_1(stationTime.getStation_1T().getName());
            stationTimeDTO.setStation_2(stationTime.getStation_2T().getName());
            stationTimeDTO.setTime(stationTime.getTime());
            stationTimeDTOList.add(stationTimeDTO);
        }
        return stationTimeDTOList;
    }


    public StationTimeDTO addNewTime(PostStationTimeDTO dto) {
        StationTime stationTime = new StationTime();
        stationTime.setStation_1T(stationRepository.getReferenceById(dto.getStations_1_id()));
        stationTime.setStation_2T(stationRepository.getReferenceById(dto.getStations_2_id()));
        stationTime.setTime(dto.getTime());
        stationTimeRepository.save(stationTime);
        StationTimeDTO stationTimeDTO = new StationTimeDTO();
        stationTimeDTO.setStation_1(stationTime.getStation_1T().getName());
        stationTimeDTO.setStation_2(stationTime.getStation_2T().getName());
        stationTimeDTO.setTime(stationTime.getTime());
        return stationTimeDTO;
    }


    public List<StationTimeDTO> addFewTime(List<PostStationTimeDTO> dtos) {
        List<StationTime> stationTimes = new ArrayList<>();
        List<StationTimeDTO> dt = new ArrayList<>();
        for (PostStationTimeDTO dto : dtos) {
            StationTime stationTime = new StationTime();
            stationTime.setStation_1T(stationRepository.getReferenceById(dto.getStations_1_id()));
            stationTime.setStation_2T(stationRepository.getReferenceById(dto.getStations_2_id()));
            stationTime.setTime(dto.getTime());
            stationTimeRepository.save(stationTime);
            StationTimeDTO stationTimeDTO = new StationTimeDTO();
            stationTimeDTO.setStation_1(stationTime.getStation_1T().getName());
            stationTimeDTO.setStation_2(stationTime.getStation_2T().getName());
            stationTimeDTO.setTime(stationTime.getTime());
            dt.add(stationTimeDTO);
        }
        return dt;
    }

    public StationTimeDTO updateTimeByKey(PostStationTimeDTO dto) {
        StationsKeyT stationsKeyT = new StationsKeyT();
        stationsKeyT.setStation_1T(dto.getStations_1_id());
        stationsKeyT.setStation_2T(dto.getStations_2_id());
        StationTime stationTime = stationTimeRepository.getReferenceById(stationsKeyT);
        stationTime.setTime(dto.getTime());
        stationTimeRepository.save(stationTime);
        StationTimeDTO stationTimeDTO = new StationTimeDTO();
        stationTimeDTO.setStation_1(stationTime.getStation_1T().getName());
        stationTimeDTO.setStation_2(stationTime.getStation_2T().getName());
        stationTimeDTO.setTime(stationTime.getTime());
        return stationTimeDTO;
    }

    public StationTimeDTO deleteByKey(PostStationTimeDTO dto) {
        StationsKeyT stationsKeyT = new StationsKeyT();
        stationsKeyT.setStation_1T(dto.getStations_1_id());
        stationsKeyT.setStation_2T(dto.getStations_2_id());
        StationTime stationTime = stationTimeRepository.findById(stationsKeyT).get();
        stationTimeRepository.delete(stationTime);
        StationTimeDTO stationTimeDTO = new StationTimeDTO();
        stationTimeDTO.setStation_1(stationTime.getStation_1T().getName());
        stationTimeDTO.setStation_2(stationTime.getStation_2T().getName());
        stationTimeDTO.setTime(stationTime.getTime());
        return stationTimeDTO;
    }
}
