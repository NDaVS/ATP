package ru.ndavs.atp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String first_name;
    private String last_name;
    private String surname;
    private String driver_id;
    private String email;
    private String phone_number;

    private String role;
    private String login;
    private String password;

    @OneToMany(mappedBy = "driver")

    private List<Trip> trip;

    private Long bus_id;

}
