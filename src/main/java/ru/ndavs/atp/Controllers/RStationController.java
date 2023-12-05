package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostRStationDTO;
import ru.ndavs.atp.Services.RStationsService;

@RestController
@RequestMapping("/apishechka/road_station")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class RStationController {
    private final RStationsService service;

    @GetMapping
    private ResponseEntity getAllRStations(){
        return ResponseEntity.ok(service.getAllRStations());
    }

    @PostMapping
    private ResponseEntity addNewRStation(@RequestBody PostRStationDTO dto){
        return ResponseEntity.ok(service.addNewRStation(dto));
    }

    @PutMapping
    private ResponseEntity updateRStation(@RequestBody PostRStationDTO dto){
        return ResponseEntity.ok(service.updateRStationByCompositeKey(dto));
    }
    @DeleteMapping
    private ResponseEntity deleteRStationByCompositeKey(@RequestBody PostRStationDTO postRStationDTO){
        return ResponseEntity.ok(service.deleteRStationByCompositeKey(postRStationDTO));
    }
}
