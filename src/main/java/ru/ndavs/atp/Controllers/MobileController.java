package ru.ndavs.atp.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.MobileDTO.TimeControlRequestDTO;
import ru.ndavs.atp.Services.AuthorizationService;
import ru.ndavs.atp.Services.MobileService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/apishechka/m")
@RequiredArgsConstructor
public class MobileController {


    private final AuthorizationService authorizationService;

    private final MobileService mobileService;

    @PostMapping(path = "/login")
    @Async
    public CompletableFuture<ResponseEntity<?>> authorization(@RequestBody AccessDTO loginRequest, @RequestParam(required = false, defaultValue = "default") String hash){
        try {
            return CompletableFuture.completedFuture(ResponseEntity.ok().body(authorizationService.mobileAuthorization(loginRequest)));
        }catch (Exception e){
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body(e.getMessage()));
        }
    }



    @PostMapping(path = "/Control")
    @Async
    public CompletableFuture<ResponseEntity<?>> timeControl(@RequestBody TimeControlRequestDTO time, @RequestParam(required = false, defaultValue = "default") String hash) {
        try {
            mobileService.markTime(time);
            return CompletableFuture.completedFuture(ResponseEntity.ok().body("Success"));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()));
        }
    }


}
