package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.ResponseDTO;

@RestController
@RequestMapping("/apishechka/m")
@RequiredArgsConstructor
public class MobileController {

    @PostMapping(path = "/login")
    public ResponseDTO authorizatin(@RequestBody AccessDTO accessDTO) {
        return null;
    }


}
