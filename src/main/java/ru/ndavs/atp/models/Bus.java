package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    @NotNull
    private String model;

    @Column(name = "vin")
    @NotNull
    private String vin;

    @Column(name = "status")
    @NotNull
    private String status;

    @Column(name = "sits")
    @NotNull
    private Integer numberOfSits;


    public Bus(
            String model,
            String vin,
            String status,
            Integer numberOfSits
    ) {
        this.model = model;
        this.vin = vin;
        this.status = status;
        this.numberOfSits = numberOfSits;
    }


    public Bus() {

    }
}
