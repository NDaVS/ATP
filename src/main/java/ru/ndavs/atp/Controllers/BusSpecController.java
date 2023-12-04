package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostBusSpecDTO;
import ru.ndavs.atp.Services.BusSpecService;

@RestController
@RequestMapping("/apishechka/bus_spec")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class BusSpecController {
    private final BusSpecService busSpecService;

    @GetMapping
    public ResponseEntity getAllSpecs() {
        return ResponseEntity.ok(busSpecService.getAllSpecs());
    }

//    @GetMapping(path = "/{model}")
//    public ResponseEntity getSpecByModel(@PathVariable String model){
//        return ResponseEntity.ok(busSpecService.getSpecByModel(model));
//    }

    @PostMapping
    public ResponseEntity addNewSpec(@RequestBody PostBusSpecDTO postBusSpecDTO) {
        return ResponseEntity.ok(busSpecService.addNewBusSpec(postBusSpecDTO));
    }

    @PutMapping
    public ResponseEntity updateBusSpec(@RequestBody PostBusSpecDTO postBusSpecDTO) {
        return ResponseEntity.ok(busSpecService.updateBusSpec(postBusSpecDTO));
    }

    @DeleteMapping(path = "")
    public ResponseEntity addNewSpec(@RequestParam String model) {
        return ResponseEntity.ok(busSpecService.deleteBusSpecByModel(model));
    }
}
