package ru.ndavs.atp.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class PostRoadDTO {
    private List<Long> stations_id;

}
