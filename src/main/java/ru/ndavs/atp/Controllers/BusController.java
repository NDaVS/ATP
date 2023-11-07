package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ndavs.atp.DTO.BusDTO;
import ru.ndavs.atp.Services.BusService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buses")
@RequiredArgsConstructor
public class BusController {
    private final ModelMapper modelMapper;
    private final BusService busService;

    @PostMapping
    public List<BusDTO> response(){
        return busService.getBuses().stream().map(bus -> modelMapper.map(bus, BusDTO.class)).toList();
    }

}
