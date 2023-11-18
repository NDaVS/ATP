package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.Repositories.BusRepository;

@RestController
@RequestMapping("/apishechka/m")
@RequiredArgsConstructor
public class MobileController {
    private BusRepository busRepository;
    private ModelMapper modelMapper;

    @PostMapping(path = "/login")
    public ResponseDTO authorizatin(@RequestBody AccessDTO accessDTO){
        return null;
    }


}
