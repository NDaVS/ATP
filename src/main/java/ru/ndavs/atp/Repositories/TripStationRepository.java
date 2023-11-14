package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.TripStations;

public interface TripStationRepository extends JpaRepository<TripStations, Long> {
}
