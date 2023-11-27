package ru.ndavs.atp.Services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.BusDTO;
import ru.ndavs.atp.DTO.PostBusDTO;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.models.Bus;

import java.util.List;

@Service
@AllArgsConstructor
public class BusService {
    private final BusRepository busRepository;
    private final ModelMapper modelMapper;

    public List<BusDTO> getBuses() {
        try {
            return busRepository.findAll().stream().map(bus -> modelMapper.map(bus, BusDTO.class)).toList();
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить список автобусов : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public BusDTO getBusById(Long id) {
        try {
            return modelMapper.map(busRepository.findById(id).get(), BusDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public BusDTO addBus(PostBusDTO postBusDTO) {
        try {
            Bus bus = modelMapper.map(postBusDTO, Bus.class);
            busRepository.save(bus);
            return modelMapper.map(bus, BusDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public BusDTO updateBusById(PostBusDTO postBusDTO, Long id) {
        try {
            Bus bus = busRepository.getReferenceById(id);
            bus.setCode(postBusDTO.getCode());
            bus.setStatus(postBusDTO.getStatus());
            bus.setModel(postBusDTO.getModel());
            bus.setNumberOfSits(postBusDTO.getNumberOfSits());
            busRepository.save(bus);
            return modelMapper.map(bus, BusDTO.class);
        } catch (Error e) {
            throw new IllegalStateException("Не удалось обновить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }

    }
    public BusDTO deleteBusById(Long id){
        try{
            Bus bus = busRepository.findById(id).get();
            busRepository.delete(bus);
            return modelMapper.map(bus, BusDTO.class);
        } catch (Exception e){
            throw new IllegalStateException("Не удалось удалить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }
}
