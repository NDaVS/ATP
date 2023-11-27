package ru.ndavs.atp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "bus")
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    @NotNull
    private String model;

    @Column(name = "code")
    @NotNull
    private String code;

    @Column(name = "status")
    @NotNull
    private String status;

    @Column(name = "sits")
    @NotNull
    private Integer numberOfSits;


    public Bus(
            String model,
            String code,
            String status,
            Integer numberOfSits
    ) {
        this.model = model;
        this.code = code;
        this.status = status;
        this.numberOfSits = numberOfSits;
    }

}
    