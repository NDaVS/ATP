package ru.ndavs.atp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Driver extends Users {
    private String driver_id;
    @OneToOne
    private Bus bus;

}
