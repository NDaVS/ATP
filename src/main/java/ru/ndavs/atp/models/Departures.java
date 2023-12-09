package ru.ndavs.atp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Departures {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String status;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    @JsonIgnoreProperties("departures")
    private List<Ticket> tickets;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", referencedColumnName = "id")
    private Trip trip;
}
