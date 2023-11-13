package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostScheduleDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.Services.ScheduleService;
import ru.ndavs.atp.models.Station;

import java.util.List;

@RestController
@RequestMapping("/apishechka/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public List<Station> getSchedule(){
        return scheduleService.getStations();
    }
    @PostMapping
    public PostScheduleDTO postSchedule(@RequestBody PostScheduleDTO postScheduleDTO){
        return postScheduleDTO;
    }
}
