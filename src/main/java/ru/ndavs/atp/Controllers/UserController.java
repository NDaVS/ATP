package ru.ndavs.atp.Controllers;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.*;
import ru.ndavs.atp.Services.UserService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/apishechka")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping(path = "/login")
    public ResponseEntity response(@RequestBody AccessDTO accessDTO) throws IllegalAccessException {
        return ResponseEntity.ok(userService.loginResponse(accessDTO));
    }

    @GetMapping(path = "/user")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok(userService.getUsers().toList().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList()));
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping(path = "/user")
    public ResponseEntity addNewUser(@RequestBody PostUserDTO postUserDTO) {
        return ResponseEntity.ok(userService.addNewUser(postUserDTO));
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity updateUserById(@RequestBody PostUserDTO postUserDTO, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateUserById(postUserDTO, id));
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity addNewUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }

    @GetMapping(path = "/driver")
    public ResponseEntity getDrivers() {
        return ResponseEntity.ok(userService.getDrivers());
    }

    @GetMapping(path = "/driver/{id}")
    public ResponseEntity getDriverById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getDriverById(id));
    }

    @PostMapping(path = "/driver")
    public ResponseEntity registerDriver(@RequestBody PostDriverDTO postDriverDTO) throws IllegalAccessException {
        return ResponseEntity.ok(userService.registerDriver(postDriverDTO));
    }

    @PutMapping(path = "/driver/{id}")
    public ResponseEntity patchDriver(@RequestBody PostDriverDTO postDriverDTO, @PathVariable Long id) {
        return ResponseEntity.ok(userService.updateDriverBus(postDriverDTO, id));
    }

    @DeleteMapping(path = "/driver/{id}")
    public ResponseEntity deleteDriver(@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteDriverById(id));
    }

    @PatchMapping(path = "/driver")
    public ResponseEntity setBusAndDriver(@RequestParam Long bus_id, @RequestParam Long driver_id){
        return ResponseEntity.ok(userService.setBusAndDriver(bus_id, driver_id));
    }


}
