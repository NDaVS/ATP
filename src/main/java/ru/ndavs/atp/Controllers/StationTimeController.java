package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostStationTimeDTO;
import ru.ndavs.atp.Services.StationTimeService;

import java.util.List;

@RestController
@RequestMapping("/apishechka/station_time")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class StationTimeController {
    private final StationTimeService service;

    @GetMapping
    private ResponseEntity<?> getAllTime(){
        return ResponseEntity.ok(service.getAllTime());
    }

    @PostMapping
    private ResponseEntity<?> addNewTime(@RequestBody PostStationTimeDTO dto){
        return ResponseEntity.ok((service.addNewTime(dto)));
    }

    @PostMapping("/few")
    private ResponseEntity<?> addFewTime(@RequestBody List<PostStationTimeDTO> dto){
        return ResponseEntity.ok((service.addFewTime(dto)));
    }

    @PutMapping
    private ResponseEntity<?> updateTime(@RequestBody PostStationTimeDTO dto){
        return ResponseEntity.ok(service.updateTimeByKey(dto));
    }

    @DeleteMapping
    private ResponseEntity<?> deleteTime(@RequestBody PostStationTimeDTO dto){
        return ResponseEntity.ok(service.deleteByKey(dto));
    }
}
