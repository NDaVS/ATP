package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
