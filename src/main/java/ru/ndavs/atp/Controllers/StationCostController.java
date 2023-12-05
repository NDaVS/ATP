package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostStationsCostDTO;
import ru.ndavs.atp.Services.StationsCostService;

@RestController
@RequestMapping("/apishechka/station_cost")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class StationCostController {
    private final StationsCostService service;

    @GetMapping
    private ResponseEntity getAllCost(){
        return ResponseEntity.ok(service.getAllCost());
    }

    @PostMapping
    private ResponseEntity addNewCost(@RequestBody PostStationsCostDTO dto){
        return ResponseEntity.ok((service.addNewCost(dto)));
    }

    @PutMapping
    private ResponseEntity updateCost(@RequestBody PostStationsCostDTO dto){
        return ResponseEntity.ok(service.updateCostByKey(dto));
    }

    @DeleteMapping ResponseEntity deleteCost(@RequestBody PostStationsCostDTO dto){
        return ResponseEntity.ok(service.deleteByKey(dto));
    }
}
