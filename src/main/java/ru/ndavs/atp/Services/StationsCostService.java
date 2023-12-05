package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.CompositeKeys.StationsCostKey;
import ru.ndavs.atp.DTO.PostStationsCostDTO;
import ru.ndavs.atp.DTO.StationDTO;
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

    public List<StationCost> getAllCost(){
        return repository.findAll();
    }

    public List<Integer> getAllCostByStation(List<StationDTO> stationDTOS){
        List<Integer> cost = new ArrayList<>();
        for (int i = 0; i < stationDTOS.size()-1; i++){
            StationsCostKey stationsCostKey = new StationsCostKey();
            stationsCostKey.setStation_1(stationDTOS.get(i).getId());
            stationsCostKey.setStation_2(stationDTOS.get(i+1).getId());
            StationCost stationCost = repository.getReferenceById(stationsCostKey);
            cost.add(stationCost.getCost());
        }
        return cost;
    }

    public StationCost addNewCost(PostStationsCostDTO dto){
        StationCost stationCost = new StationCost();
        stationCost.setStation_1(stationRepository.getReferenceById(dto.getStations_1_id()));
        stationCost.setStation_2(stationRepository.getReferenceById(dto.getStations_2_id()));
        stationCost.setCost(dto.getCost());
        repository.save(stationCost);
        return stationCost;
    }

    public StationCost updateCostByKey(PostStationsCostDTO dto){
        StationsCostKey stationsCostKey = new StationsCostKey();
        stationsCostKey.setStation_1(dto.getStations_1_id());
        stationsCostKey.setStation_2(dto.getStations_2_id());
        StationCost stationCost = repository.getReferenceById(stationsCostKey);
        stationCost.setCost(dto.getCost());
        repository.save(stationCost);
        return stationCost;
    }

    public StationCost deleteByKey(PostStationsCostDTO dto){
        StationsCostKey stationsCostKey = new StationsCostKey();
        stationsCostKey.setStation_1(dto.getStations_1_id());
        stationsCostKey.setStation_2(dto.getStations_2_id());
        StationCost stationCost = repository.findById(stationsCostKey).get();
        repository.delete(stationCost);
        return stationCost;

    }
}
