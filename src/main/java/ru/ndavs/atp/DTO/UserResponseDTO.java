package ru.ndavs.atp.DTO;

import lombok.Data;

@Data
public class UserResponseDTO {
    public String token;
    public Long id;
    public String first_name;
    public String last_name;
    public String father_name;
    public String email;
    public String phone_number;
    public String role;
}
