package ru.ndavs.atp.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserDTO{
    public String firstName;
    public String lastName;
    public String fatherName;
    public String email;
    public String role;
}
