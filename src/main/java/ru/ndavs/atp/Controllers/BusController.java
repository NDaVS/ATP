package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.PostBusDTO;
import ru.ndavs.atp.Services.BusService;

@RestController
@RequestMapping("/apishechka/buses")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class BusController {
    private final BusService busService;

    @GetMapping
    public ResponseEntity getAllBuses() {
        return ResponseEntity.ok(busService.getBuses());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getBusById(@PathVariable Long id) {
        return ResponseEntity.ok(busService.getBusById(id));
    }

    @PostMapping
    public ResponseEntity addBus(@RequestBody PostBusDTO postbusDTO) {
        return ResponseEntity.ok(busService.addBus(postbusDTO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity updateBusById(@PathVariable Long id, @RequestBody PostBusDTO postBusDTO) {
        return ResponseEntity.ok(busService.updateBusById(postBusDTO, id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteBusById(@PathVariable Long id) {
        return ResponseEntity.ok(busService.deleteBusById(id));
    }
}
