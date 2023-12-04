package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Data;

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
    private String role;
    private String login;
    private String password;
    @OneToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

}
