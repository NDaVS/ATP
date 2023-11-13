package ru.ndavs.atp.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ndavs.atp.models.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
}
