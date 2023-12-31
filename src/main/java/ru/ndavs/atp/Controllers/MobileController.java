package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.MobileDTO.PassengersControlRequestDTO;
import ru.ndavs.atp.MobileDTO.TimeControlRequestDTO;
import ru.ndavs.atp.Services.AuthorizationService;
import ru.ndavs.atp.Services.MobileService;

@RestController
@RequestMapping("/apishechka/m")
@RequiredArgsConstructor
public class MobileController {


    private final AuthorizationService authorizationService;

    private final MobileService mobileService;

    @PostMapping(path = "/login")
    public ResponseEntity<?> authorization(@RequestBody AccessDTO loginRequest, @RequestParam(required = false, defaultValue = "default") String hash){
        try {
            return ResponseEntity.ok().body(authorizationService.mobileAuthorization(loginRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



    @PostMapping(path = "/Control")
    public ResponseEntity<?> timeControl(@RequestBody TimeControlRequestDTO time, @RequestParam(required = false, defaultValue = "default") String hash) {
        try {
            mobileService.markTime(time);
            return ResponseEntity.ok().body("Success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
