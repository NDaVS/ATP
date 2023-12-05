package ru.ndavs.atp.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserDTO{
    public Long id;
    public String first_name;
    public String last_name;
    public String surname;
    public String email;
    private String phone_number;

    public String role;
}
