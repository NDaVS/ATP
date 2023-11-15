package ru.ndavs.atp.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserDTO{
    public String first_name;
    public String last_name;
    public String father_name;
    public String email;
    public String role;
}
