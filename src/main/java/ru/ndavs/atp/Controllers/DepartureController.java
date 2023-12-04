package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostDepartureDTO;
import ru.ndavs.atp.Services.DepartureService;

@RestController
@RequestMapping("/apishechka/departures")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class DepartureController {
    private final DepartureService departureService;

    @GetMapping
    private ResponseEntity getAllDepartures(){
        return ResponseEntity.ok(departureService.getAllDepartures());
    }

    @GetMapping(path = "/{id}")
    private ResponseEntity getDepartureByid(@PathVariable Long id){
        return ResponseEntity.ok(departureService.getDepartureById(id));
    }

    @PostMapping
    private ResponseEntity addNewDeparture(@RequestBody PostDepartureDTO postDepartureDTO){
        return ResponseEntity.ok(departureService.addNewDeparture(postDepartureDTO));
    }

    @PutMapping(path = "/{id}")
    private ResponseEntity updateDepartureById(@RequestBody PostDepartureDTO postDepartureDTO, @PathVariable Long id){
        return ResponseEntity.ok(departureService.updateDepartureById(postDepartureDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    private ResponseEntity deleteDdepartureById(@PathVariable Long id){
        return ResponseEntity.ok(departureService.deleteDepartureById(id));
    }
}
