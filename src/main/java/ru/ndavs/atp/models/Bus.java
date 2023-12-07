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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    @Column(name = "code")
    @NotNull
    private String code;

    @Column(name = "status")
    @NotNull
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model", referencedColumnName = "model")
    private BusSpecs busSpec;

    @Column(name = "sits")
    @NotNull
    private Integer numberOfSits;


    public Bus(
            String code,
            String status
    ) {

        this.code = code;
        this.status = status;
    }

}
    