package ru.ndavs.atp.DTO;

import lombok.Data;

@Data
public class PostUserDTO {
    private String first_name;
    private String last_name;
    private String surname;
    private String email;
    private String role;
    private String login;
    private String password;

}
