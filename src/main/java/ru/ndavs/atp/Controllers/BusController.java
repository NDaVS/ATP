package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ndavs.atp.DTO.BusDTO;
import ru.ndavs.atp.Services.BusService;

import java.util.List;

@RestController
@RequestMapping("/apishechka/buses")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class BusController {
    private final ModelMapper modelMapper;
    private final BusService busService;

    @GetMapping
    public List<BusDTO> response(){
        return busService.getBuses().stream().map(bus -> modelMapper.map(bus, BusDTO.class)).toList();
    }

}
