package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.*;
import ru.ndavs.atp.Services.TripService;
import ru.ndavs.atp.models.Schedule;
import ru.ndavs.atp.models.TripStations;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apishechka/schedule")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class ScheduleController {

    private final TripService tripService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<TripStationDTO> getSchedule(){
        List<TripStations>  trips = tripService.getTrip();
        List<TripStationDTO> tripStationDTOList = new ArrayList<>();
        for (TripStations trip : trips) {
            TripStationDTO tripStationDTO = modelMapper.map(trip, TripStationDTO.class);
            tripStationDTO.setDriverDTO(modelMapper.map(trip.getDriver(), DriverDTO.class));
            tripStationDTO.driverDTO.setBusDTO(modelMapper.map(trip.getDriver().getBus(), BusDTO.class));
//            tripStationDTO.setStation(modelMapper.map(schedule.getStations(), TripStationDTO.class));
            tripStationDTOList.add(tripStationDTO);
        }
        return tripStationDTOList;
    }


    @PostMapping
    public TripStationDTO postSchedule(@RequestBody PostTripDTO postTripDTO){
        TripStations tripStations = tripService.addTrip(postTripDTO);
        TripStationDTO tripStationDTO = modelMapper.map(tripStations, TripStationDTO.class);
        tripStationDTO.setDriverDTO(modelMapper.map(tripStations.getDriver(), DriverDTO.class));
        tripStationDTO.driverDTO.setBusDTO(modelMapper.map(tripStations.getDriver().getBus(), BusDTO.class));
//        tripStationDTO.setStation(modelMapper.map(schedule.getStations(), TripStationDTO.class));
        return tripStationDTO;
    }
}
