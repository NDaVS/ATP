package ru.ndavs.atp.DTO;

import lombok.Data;

@Data
public class RegisterUserDTO extends UserDTO{
    public String login;
    public String password;
}
