package ru.ndavs.atp.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {
    public String token;
    public String role;

    public LoginResponseDTO(String token, String role) {
        this.token = token;
        this.role = role;
    }
}
