package ru.ndavs.atp.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@RequiredArgsConstructor
public class AccessDTO {
    public String login;
    public String password;
    public Date date;
}
