package ru.ndavs.atp.Services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.PostBusSpecDTO;
import ru.ndavs.atp.Repositories.BusSpecRepository;
import ru.ndavs.atp.models.BusSpecs;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusSpecService {
    private final BusSpecRepository busSpecRepository;
    private final ModelMapper modelMapper;

    public List<BusSpecs> getAllSpecs(){
        return busSpecRepository.findAll();
    }

    public BusSpecs getSpecByModel(String model){
        return busSpecRepository.getReferenceById(model);
    }

    public BusSpecs addNewBusSpec(PostBusSpecDTO postBusSpecDTO){
        BusSpecs busSpec = modelMapper.map(postBusSpecDTO, BusSpecs.class);
        busSpecRepository.save(busSpec);
        return busSpec;
    }

    public BusSpecs updateBusSpec(PostBusSpecDTO postBusSpecDTO){
        BusSpecs busSpecs = busSpecRepository.getReferenceById(postBusSpecDTO.getModel());
        busSpecs.setNumber_of_sits(postBusSpecDTO.getNumber_of_sits());
        busSpecRepository.save(busSpecs);
        return busSpecs;
    }

    public BusSpecs deleteBusSpecByModel(String model){
        BusSpecs busSpec = busSpecRepository.findById(model).get();
        busSpecRepository.delete(busSpec);
        return busSpec;
    }
}
