package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.CompositeKeys.StationsKeyC;
import ru.ndavs.atp.CompositeKeys.StationsKeyT;
import ru.ndavs.atp.DTO.PostStationsCostDTO;
import ru.ndavs.atp.DTO.StationCostDTO;
import ru.ndavs.atp.Repositories.StationCostRepository;
import ru.ndavs.atp.Repositories.StationRepository;
import ru.ndavs.atp.models.StationCost;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StationsCostService {
    private final StationCostRepository repository;
    private final StationRepository stationRepository;

    public List<StationCostDTO> getAllCost() {
        List<StationCost> stationsCost = repository.findAll();
        List<StationCostDTO> stationCostDTOList = new ArrayList<>();
        for (StationCost stationCost : stationsCost) {
            StationCostDTO stationCostDTO = new StationCostDTO();
            stationCostDTO.setStation_1(stationCost.getStation_1C().getId());
            stationCostDTO.setStation_2(stationCost.getStation_2C().getId());
            stationCostDTO.setCost(stationCost.getCost());
            stationCostDTOList.add(stationCostDTO);
        }
        return stationCostDTOList;
    }


    public StationCostDTO addNewCost(PostStationsCostDTO dto) {
        StationCost stationCost = new StationCost();
        stationCost.setStation_1C(stationRepository.getReferenceById(dto.getStations_1_id()));
        stationCost.setStation_2C(stationRepository.getReferenceById(dto.getStations_2_id()));
        stationCost.setCost(dto.getCost());
        repository.save(stationCost);
        StationCostDTO stationCostDTO = new StationCostDTO();
        stationCostDTO.setStation_1(stationCost.getStation_1C().getId());
        stationCostDTO.setStation_2(stationCost.getStation_2C().getId());
        stationCostDTO.setCost(stationCost.getCost());
        return stationCostDTO;
    }

    public List<StationCostDTO> addFewCost(List<PostStationsCostDTO> dtos) {
        List<StationCost> stationCosts = new ArrayList<>();
        List<StationCostDTO> stationCostDTOList = new ArrayList<>();
        for (PostStationsCostDTO dto : dtos) {
            StationCost stationCost = new StationCost();
            stationCost.setStation_1C(stationRepository.getReferenceById(dto.getStations_1_id()));
            stationCost.setStation_2C(stationRepository.getReferenceById(dto.getStations_2_id()));
            stationCost.setCost(dto.getCost());
            repository.save(stationCost);
            StationCostDTO stationCostDTO = new StationCostDTO();
            stationCostDTO.setStation_1(stationCost.getStation_1C().getId());
            stationCostDTO.setStation_2(stationCost.getStation_2C().getId());
            stationCostDTO.setCost(stationCost.getCost());
            stationCostDTOList.add(stationCostDTO);
        }
        return stationCostDTOList;
    }

    public StationCostDTO updateCostByKey(PostStationsCostDTO dto) {
        StationsKeyC stationsKeyC = new StationsKeyC();
        stationsKeyC.setStation_1C(dto.getStations_1_id());
        stationsKeyC.setStation_2C(dto.getStations_2_id());
        StationCost stationCost = repository.getReferenceById(stationsKeyC);
        stationCost.setCost(dto.getCost());
        repository.save(stationCost);
        StationCostDTO stationCostDTO = new StationCostDTO();
        stationCostDTO.setStation_1(stationCost.getStation_1C().getId());
        stationCostDTO.setStation_2(stationCost.getStation_2C().getId());
        stationCostDTO.setCost(stationCost.getCost());
        return stationCostDTO;
    }

    public StationCostDTO deleteByKey(PostStationsCostDTO dto) {
        StationsKeyC stationsKeyC = new StationsKeyC();
        stationsKeyC.setStation_1C(dto.getStations_1_id());
        stationsKeyC.setStation_2C(dto.getStations_2_id());
        StationCost stationCost = repository.findById(stationsKeyC).get();
        repository.delete(stationCost);
        StationCostDTO stationCostDTO = new StationCostDTO();
        stationCostDTO.setStation_1(stationCost.getStation_1C().getId());
        stationCostDTO.setStation_2(stationCost.getStation_2C().getId());
        stationCostDTO.setCost(stationCost.getCost());
        return stationCostDTO;

    }
}
