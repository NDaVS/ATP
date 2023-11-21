package ru.ndavs.atp.DTO;

import lombok.*;

@Data
@NoArgsConstructor
public class ResponseDTO {
    public Long code;
    public String message;
    public Object data;
//    public String token;
//    public String first_name;
//    public String last_name;
//    public String role;
    // перемещено в UserResponseDTO

}
