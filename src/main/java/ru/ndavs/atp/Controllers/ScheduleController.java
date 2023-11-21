package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostTripDTO;
import ru.ndavs.atp.Services.TripService;

@RestController
@RequestMapping("/apishechka/schedule")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class ScheduleController {

    private final TripService tripService;

    @GetMapping
    public ResponseEntity getSchedule() {
        return ResponseEntity.ok(tripService.getTrip());
    }


    @PostMapping
    public ResponseEntity addNewTrip(@RequestBody PostTripDTO postTripDTO) {
        return ResponseEntity.ok(tripService.addTrip(postTripDTO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateTrip(@PathVariable Long id, @RequestBody PostTripDTO postTripDTO) {
        return ResponseEntity.ok(tripService.updateTrip(id, postTripDTO));
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteTrip(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.deleteTrip(id));
    }
}
