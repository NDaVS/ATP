package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Departures;
import ru.ndavs.atp.models.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<Ticket> findTicketsByDepartures(Departures departures);
}
