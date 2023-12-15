package ru.ndavs.atp.Services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.ndavs.atp.DTO.BusDTO;
import ru.ndavs.atp.DTO.PostBusDTO;
import ru.ndavs.atp.Repositories.BusRepository;
import ru.ndavs.atp.Repositories.BusSpecRepository;
import ru.ndavs.atp.models.Bus;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BusService {
    private final BusRepository busRepository;
    private final BusSpecRepository busSpecRepository;
    private final ModelMapper modelMapper;

    public List<BusDTO> getBuses() {
        try {
            List<BusDTO> buses = new ArrayList<>();
            List<Bus> busList = busRepository.findAll();
            if (busList.isEmpty()){
                return buses;
            }
            for (Bus bus : busRepository.findAll()) {
                BusDTO busDTO = modelMapper.map(bus, BusDTO.class);
                busDTO.setModel(bus.getBusSpec().getModel());
                busDTO.setNumber_of_sits(bus.getBusSpec().getNumber_of_sits());
                if (bus.getDriver() != null) {
                    busDTO.setDrive_id(bus.getDriver().getId());
                }
                buses.add(busDTO);
            }
            return buses;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить список автобусов : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public BusDTO getBusById(Long id) {
        try {
            Bus bus = busRepository.findById(id).get();
            BusDTO busDTO = modelMapper.map(bus, BusDTO.class);
            busDTO.setNumber_of_sits(bus.getBusSpec().getNumber_of_sits());
            busDTO.setModel(bus.getBusSpec().getModel());
            if (bus.getDriver() != null) {
                busDTO.setDrive_id(bus.getDriver().getId());
            }
            return busDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось получить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public BusDTO addBus(PostBusDTO postBusDTO) {
        try {
            Bus bus = modelMapper.map(postBusDTO, Bus.class);
            bus.setBusSpec(busSpecRepository.findById(postBusDTO.getModel()).get());
            busRepository.save(bus);
            BusDTO busDTO = modelMapper.map(bus, BusDTO.class);
            busDTO.setNumber_of_sits(bus.getBusSpec().getNumber_of_sits());
            busDTO.setModel(bus.getBusSpec().getModel());
            return busDTO;
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось добавить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }

    public BusDTO updateBusById(PostBusDTO postBusDTO, Long id) {
        try {
            Bus bus = busRepository.getReferenceById(id);
            bus.setCode(postBusDTO.getCode());
            bus.setStatus(postBusDTO.getStatus());

            bus.setBusSpec(busSpecRepository.findById(postBusDTO.getModel()).get());
            busRepository.save(bus);
            BusDTO busDTO = modelMapper.map(bus, BusDTO.class);
            busDTO.setNumber_of_sits(bus.getBusSpec().getNumber_of_sits());
            busDTO.setModel(bus.getBusSpec().getModel());
            if (bus.getDriver() != null){
                busDTO.setDrive_id(bus.getDriver().getId());
            }
            return busDTO;
        } catch (Error e) {
            throw new IllegalStateException("Не удалось обновить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }

    }

    public BusDTO deleteBusById(Long id) {
        try {
            Bus bus = busRepository.findById(id).get();
            busRepository.delete(bus);
            bus.setBusSpec(null);
            return modelMapper.map(bus, BusDTO.class);
        } catch (Exception e) {
            throw new IllegalStateException("Не удалось удалить автобус : " + e.getMessage() + " | | " + e.getStackTrace());
        }
    }
}
