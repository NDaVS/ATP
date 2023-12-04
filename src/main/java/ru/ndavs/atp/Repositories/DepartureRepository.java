package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Departures;

public interface DepartureRepository extends JpaRepository<Departures, Long> {
}
