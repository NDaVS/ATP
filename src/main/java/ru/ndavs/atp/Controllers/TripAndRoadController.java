package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostRoadDTO;
import ru.ndavs.atp.DTO.PostTripDTO;
import ru.ndavs.atp.Services.RoadService;
import ru.ndavs.atp.Services.TripService;

@RestController
@RequestMapping("/apishechka/")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class TripAndRoadController {

    private final RoadService roadService;

    private final TripService tripService;

    @GetMapping(path = "/stations")
    public ResponseEntity getStations() {
        return ResponseEntity.ok(roadService.getStations());
    }

    //    ROAD CRUD
    @GetMapping(path = "/road")
    public ResponseEntity getRoad() {
        return ResponseEntity.ok(roadService.getRoads());
    }

    @GetMapping(path = "/road/{id}")
    public ResponseEntity getRoadById(@PathVariable Long id){
        return ResponseEntity.ok(roadService.getRoadById(id));
    }

    @PostMapping(path = "/road")
    public ResponseEntity addNewRoad(@RequestBody PostRoadDTO postRoadDTO) {
        return ResponseEntity.ok(roadService.addRoad(postRoadDTO));
    }

    @PutMapping(path = "/road/{id}")
    public ResponseEntity updateRoad(@PathVariable Long id, @RequestBody PostRoadDTO postRoadDTO) {
        return ResponseEntity.ok(roadService.updateTrip(id, postRoadDTO));
    }

    @DeleteMapping(path = "/road/{id}")
    public ResponseEntity deleteRoad(@PathVariable Long id) {
        return ResponseEntity.ok(roadService.deleteTrip(id));
    }

    //    TRIP CRUD
    @GetMapping(path = "/trip")
    public ResponseEntity getTrips() {
        return ResponseEntity.ok(tripService.getAllTrips());
    }
    @GetMapping(path = "/trip/{id}")
    public ResponseEntity getTripById(@PathVariable Long id){
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @PostMapping(path = "/trip")
    public ResponseEntity addNewTrip(@RequestBody PostTripDTO postRoadDTO){
        return ResponseEntity.ok(tripService.addNewTrip(postRoadDTO));
    }
//
    @PutMapping(path = "/trip/{id}")
    public ResponseEntity updateTrip(@RequestBody PostTripDTO postTripDTO, @PathVariable Long id){
        return ResponseEntity.ok(tripService.updateTripById(postTripDTO, id));
    }
//
    @DeleteMapping(path = "/trip/{id}")
    public ResponseEntity deleteTripById(@PathVariable Long id){
        return ResponseEntity.ok(tripService.deleteTripById(id));
    }

}
