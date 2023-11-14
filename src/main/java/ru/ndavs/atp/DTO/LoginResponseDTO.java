package ru.ndavs.atp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class LoginResponseDTO {
    public String token;
    public String first_name;
    public String last_name;
    public String role;

}
