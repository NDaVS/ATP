package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Station;
import ru.ndavs.atp.models.TripStation;

public interface TripStationRepository extends JpaRepository<TripStation, Long> {
}

