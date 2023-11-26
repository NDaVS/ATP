package ru.ndavs.atp.Controllers;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.RegisterDriverDTO;
import ru.ndavs.atp.DTO.ResponseDTO;
import ru.ndavs.atp.DTO.UserDTO;
import ru.ndavs.atp.Services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apishechka")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ylzaporozhskiy.ru/")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getUsers().toList().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    @PostMapping(path = "/login")
    public ResponseDTO response(@RequestBody AccessDTO accessDTO) throws IllegalAccessException {
        return userService.loginResponse(accessDTO);
    }

    @PostMapping(path = "/register_driver")
    public ResponseDTO registerDriver(@RequestBody RegisterDriverDTO registerDriverDTO) throws IllegalAccessException {
        return userService.registerDriver(registerDriverDTO);
    }

    @GetMapping(path = "/drivers")
    public ResponseDTO getDrivers() {
        return userService.getDrivers();
    }

    @PatchMapping(path = "/drivers")
    public ResponseDTO patchDriver(@RequestParam Long driver_id, @RequestParam Long bus_id) {
        return userService.updateDriverBus(driver_id, bus_id);
    }


}
