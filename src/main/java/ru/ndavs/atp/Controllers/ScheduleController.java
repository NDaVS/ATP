package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostScheduleDTO;
import ru.ndavs.atp.DTO.StationDTO;
import ru.ndavs.atp.Services.ScheduleService;
import ru.ndavs.atp.models.Schedule;
import ru.ndavs.atp.models.Station;

import java.util.List;

@RestController
@RequestMapping("/apishechka/schedule")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public List<Schedule> getSchedule(){
        return scheduleService.getSchedule();
    }
    @PostMapping
    public Schedule postSchedule(@RequestBody PostScheduleDTO postScheduleDTO){
        return scheduleService.addTrip(postScheduleDTO);
    }
}
