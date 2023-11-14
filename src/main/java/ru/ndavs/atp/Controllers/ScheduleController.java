package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.BusDTO;
import ru.ndavs.atp.DTO.PostScheduleDTO;
import ru.ndavs.atp.DTO.ScheduleDTO;
import ru.ndavs.atp.DTO.TripStationDTO;
import ru.ndavs.atp.Services.ScheduleService;
import ru.ndavs.atp.models.Bus;
import ru.ndavs.atp.models.Schedule;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apishechka/schedule")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<ScheduleDTO> getSchedule(){
        List<Schedule>  schedules= scheduleService.getSchedule();
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
            scheduleDTO.setBusDTO(modelMapper.map(schedule.getBus(), BusDTO.class));
            scheduleDTO.setTripStationDTO(modelMapper.map(schedule.getStations(), TripStationDTO.class));
            scheduleDTOList.add(scheduleDTO);
        }
        return scheduleDTOList;
    }


    @PostMapping
    public ScheduleDTO postSchedule(@RequestBody PostScheduleDTO postScheduleDTO){
        Schedule schedule = scheduleService.addTrip(postScheduleDTO);
        ScheduleDTO scheduleDTO = modelMapper.map(schedule, ScheduleDTO.class);
        scheduleDTO.setBusDTO(modelMapper.map(schedule.getBus(), BusDTO.class));
        scheduleDTO.setTripStationDTO(modelMapper.map(schedule.getStations(), TripStationDTO.class));
        return scheduleDTO;
    }
}
