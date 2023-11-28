package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostStationDTO;
import ru.ndavs.atp.Services.StationService;

@RestController
@RequestMapping("/apishechka/stations")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class StationController {
    private final StationService stationService;

    @GetMapping
    public ResponseEntity getStations() {
        return ResponseEntity.ok(stationService.getStations());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getStationById(@PathVariable Long id) {
        return ResponseEntity.ok(stationService.getStationById(id));
    }

    @PostMapping
    public ResponseEntity addStation(@RequestBody PostStationDTO postStationDTO) {
        return ResponseEntity.ok(stationService.addNewStation(postStationDTO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateStationById(@RequestBody PostStationDTO postStationDTO, @PathVariable Long id) {
        return ResponseEntity.ok(stationService.updateStationById(postStationDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteStationById(@PathVariable long id) {
        return ResponseEntity.ok(stationService.deleteStationById(id));
    }
}
