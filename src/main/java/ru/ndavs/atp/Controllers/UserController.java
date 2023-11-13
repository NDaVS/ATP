package ru.ndavs.atp.Controllers;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;
import ru.ndavs.atp.DTO.AccessDTO;
import ru.ndavs.atp.DTO.LoginResponseDTO;
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
    public List<UserDTO> getUsers(){
        return userService.getUsers().toList().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
    @PostMapping(path = "/login")
    public LoginResponseDTO response(@RequestBody AccessDTO accessDTO) throws IllegalAccessException {
        return userService.loginResponse(accessDTO);
    }



}
